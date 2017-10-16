package advjava.Assignment3;

/**
 * @author Nathan Boehning
 * Date: 10/16/2017
 * Purpose: Complete the instant messaging assignment 3, client portion
 */

import java.util.Scanner;
import java.io.*;
import java.net.*;

public class IMClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 8000);
        
        ReceiveServerMessageTask receiveTask = new ReceiveServerMessageTask(socket);
        Thread thread = new Thread(receiveTask);
        thread.start();

        SendServerMessageTask sendTask = new SendServerMessageTask(socket);
        thread = new Thread(sendTask);
        thread.start();
        
        System.out.println("You're Connected, Say Hello!");
    }
}

class ReceiveServerMessageTask implements Runnable {
    private Socket socket;
        
    ReceiveServerMessageTask(Socket socket) {
        this.socket = socket;
    }
        
    @Override
    public void run() {
        try {
            ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
            while(true) {
                try {
                    String message = (String)fromServer.readObject();
                    System.out.println("Server: " + message);
                }
                catch(ClassNotFoundException e2) {}
            }
        } 
        catch(IOException e1) {}
    }
}
    
class SendServerMessageTask implements Runnable {
    private Socket socket;
        
    SendServerMessageTask (Socket socket) {
        this.socket = socket;
    }
        
    @Override
    public void run() {
        Scanner input = new Scanner(System.in);
        try {
            ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
            while(true) {
                String message = input.nextLine();
                toServer.writeObject(message);
            }
        } 
        catch(IOException e1) {}
    }
}
