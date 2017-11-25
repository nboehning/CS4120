package advjava.Midterm2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Nathan Boehning
 * Date: 11/16/2017
 * Purpose: Server component of a chat room application
 */
public class Q3_ChatRoomServer {
    
    private static ArrayList<Client> clientList = new ArrayList<Client>();
    private static LinkedBlockingQueue<String> messages = new LinkedBlockingQueue<String>();
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Create the server socket
        ServerSocket serverImSocket = new ServerSocket(8000);
        
        // Thread to accept incoming clients
        Thread acceptClient = new Thread() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Socket newClient = serverImSocket.accept();
                        clientList.add(new Client(newClient));
                    }
                    catch(IOException | NullPointerException e) {}
                }
            }
        };
        
        // start the thread to accept clients
        acceptClient.start();
        
        
        // Create a thread that sends messages out to the clients as they
        // get added to the message queue
        Thread sendMessages = new Thread() {
            @Override
            public void run() {
                while(true) {
                    try {
                        String toSend = messages.take();
                        for(int i = 0; i < clientList.size(); i++) {
                            clientList.get(i).writeMsg(toSend);
                        }
                        System.out.println("Message Sent from server: " + toSend);
                    } 
                    catch (InterruptedException | IOException | NullPointerException ex) {}
                }
            }
        };
        
        // Start the thread to send messages as they're receieved
        sendMessages.start();
    }
    
    static class Client {
        ObjectInputStream in;
        ObjectOutputStream out;
        Socket sock;

        Client(Socket sock) {
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
                            System.out.println("Message received from client: " + inMsg);
                        } 
                        catch (IOException | ClassNotFoundException | NullPointerException ex) {}
                    }
                }
            };

            // Start the thread
            readMessage.start();
        }
        
        // Give a method to write out to this particular client
        public void writeMsg(String msg) throws IOException {
            out.writeObject(msg);
        }
    }
}  // End of class Q3_ChatRoomServer
