package PacMan;

/**
 * @author Nathan Boehning
 * Date: 
 * Purpose:
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class GameServer {
    
    private static ArrayList<Player> playerList = new ArrayList<Player>();
    private static LinkedBlockingQueue<String> messages = new LinkedBlockingQueue<String>();  
    private static int numPlayers = 0;
    private static ServerSocket serverGameSocket;
    
    public static void startServer() throws IOException, ClassNotFoundException {
        // Create the server socket
        serverGameSocket = new ServerSocket(8000);
               
        System.out.println("Starts the server");
        // Thread to accept incoming clients
        Thread acceptClient = new Thread() {
            @Override
            public void run() {
                while(true) {
                try {
                        Socket newPlayer = serverGameSocket.accept();
                        numPlayers++;
                        // Capture the current number of players in case another joins
                        int playerNum = numPlayers;

                        // Add to the playerList
                        playerList.add(new Player(newPlayer, playerNum));
                    }
                    catch(IOException | NullPointerException e) {}
                    
                    System.out.println("SERVER: Added a new player");
                }
            }
        };
        
        // start the thread to accept clients
        acceptClient.start();
        
        // Create a thread that sends messages out to the players as they
        // get added to the message queue
        Thread sendMessages = new Thread() {
            @Override
            public void run() {
                while(true) {
                    try {
                        String toSend = messages.take();

                        for(int i = 0; i < playerList.size(); i++) {
                            playerList.get(i).writeMsg(toSend);
                        }
                    } 
                    catch (InterruptedException | IOException | NullPointerException ex) {}
                }
            }
        };
        
        // Start the thread to send messages as they're receieved
        sendMessages.start();
    }
    
    public static void stopServer() {
        if(serverGameSocket.isClosed())
            return;
        
        try {
            serverGameSocket.close();
            playerList.clear();
        } catch (IOException ex) {}
        
    }
    
    static class Player {
        ObjectInputStream in;
        ObjectOutputStream out;
        Socket sock;

        Player(Socket sock, int playerNum) {
            // Set the socket, in, and out streams for this client
            this.sock = sock;
            try {
                in = new ObjectInputStream(sock.getInputStream());
                out = new ObjectOutputStream(sock.getOutputStream());
            }
            catch(IOException e1) {}

            // Thread to read in the messages from the client
            // And add to the stack of messages to be sent to the clients
            Thread readMessage = new Thread() {
                @Override
                public void run() {
                    while(true) {
                        try {
                            String inMsg = (String) in.readObject();
                            messages.add(inMsg);
                        } 
                        catch (IOException | ClassNotFoundException | NullPointerException ex) {}
                    }
                }
            };

            // Start the thread
            readMessage.start();
            
            // send a newplayer message to all players so they create this instance locally
            messages.add("NEWPLAYER " + playerNum);
        }
        
        // Give a method to write out to this particular client
        public void writeMsg(String msg) throws IOException {
            out.writeObject(msg);
        }
    }
    
    
} // End of class GameServer
