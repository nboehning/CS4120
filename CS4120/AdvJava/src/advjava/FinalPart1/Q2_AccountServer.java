package advjava.FinalPart1;

/**
 * @author Nathan Boehning
 * Date: 11/25/2017
 * Purpose: Allows clients to create a username and password in a database
 */

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Q2_AccountServer {
        
    static Connection conn;
    static PreparedStatement checkUsernameStatement;
    static PreparedStatement addUsernameStatement;

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded.");
        
        conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/midterm?user=scott&password=tiger");
        System.out.println("Connection Established");
        
        checkUsernameStatement = conn.prepareStatement("select username from account where username = ?");
        addUsernameStatement = conn.prepareStatement("insert into account(username, password) values(?, ?)");
                       
        ServerSocket servSock = new ServerSocket(8000);
        int numClient = 0;
        
        System.out.println("Networking setup. Waiting for client.");
        
        while(true) {            
            Socket socket = servSock.accept();
            numClient++;
            
            System.out.println("Server connected to client " + numClient + " at " + socket.getInetAddress());
            
            CreateNewAccountTask task = new CreateNewAccountTask(socket, numClient);
            Thread thread = new Thread(task);
            thread.start();
        }
    }
    
    static class CreateNewAccountTask implements Runnable {
    private Socket socket;
    private int clientID;
    
    CreateNewAccountTask(Socket sock, int clientId) {
        socket = sock;
        clientID = clientId;
    }
    
    @Override
    public void run() {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream toClient = new ObjectOutputStream(socket.getOutputStream());
            
            while(true) {
                try {
                    String username = (String) fromClient.readObject();
                    String password = (String) fromClient.readObject();
                    
                    checkUsernameStatement.setString(1, username);
                    ResultSet result = checkUsernameStatement.executeQuery();

                    if(!result.next()) {
                        // There's no data, so insert the username and password to the table
                        addUsernameStatement.setString(1, username);
                        addUsernameStatement.setString(2, password);
                        addUsernameStatement.execute();

                        System.out.println("Client " + clientID + ": Account '"+username+"' with password '"+password+"' created.");
                        toClient.writeObject("Account created");
                    }
                    else {
                        // There is data, so the username already exists, don't insert into table
                        System.out.println("Client " + clientID + ": Account '"+username+"' already exists");
                        toClient.writeObject(username + " already exists in the database");
                    }
                } 
                catch (IOException | ClassNotFoundException | NullPointerException | SQLException ex) {}
            }
        
        } catch(IOException e) {}
    }
}
}

