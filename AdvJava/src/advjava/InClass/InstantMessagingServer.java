package advjava.InClass;

/**
 * @author Nathan Boehning
 * Date: 
 * Purpose: 
 */

import java.util.Scanner;
import java.io.*;
import java.net.*;

public class InstantMessagingServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        Scanner input = new Scanner(System.in);
        ServerSocket serverSocket = new ServerSocket(8000);
        
        System.out.println("Waiting for client");
        
        Socket socket = serverSocket.accept();
        
        System.out.println("Client connected");
        
        ObjectOutputStream toClient = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream fromClient = new ObjectInputStream(socket.getInputStream());
        
        while(true) {
            String message = (String)fromClient.readObject();
            System.out.println("Client: " + message);
            
            System.out.print("Enter Message: ");
            message = input.nextLine();
            toClient.writeObject(message);
        }
    }
}
