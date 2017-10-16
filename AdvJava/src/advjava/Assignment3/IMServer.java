package advjava.Assignment3;

/**
 * @author Nathan Boehning
 * Date: 10/16/2017
 * Purpose: Complete the instant messaging assignment 3, server portion
 */

import java.util.Scanner;
import java.io.*;
import java.net.*;

public class IMServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        ServerSocket serverImSocket = new ServerSocket(8000);
        int numClient = 0;
        
        while(true) {
            Socket socket = serverImSocket.accept();           
            
            ReceiveClientMessageTask receiveTask = new ReceiveClientMessageTask(socket);
            Thread thread = new Thread(receiveTask);
            thread.start();
            
            SendClientMessageTask sendTask = new SendClientMessageTask(socket);
            thread = new Thread(sendTask);
            thread.start();
            
            System.out.println("You're Connected, Say Hello!");
        }
    }
}

class ReceiveClientMessageTask implements Runnable {
    private Socket socket;
        
    ReceiveClientMessageTask(Socket socket) {
        this.socket = socket;
    }
        
    @Override
    public void run() {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(socket.getInputStream());
            while(true) {
                try {
                    String message = (String)fromClient.readObject();
                    System.out.println("Client: " + message);
                }
                catch(ClassNotFoundException e2) {}
            }
        } 
        catch(IOException e1) {}
    }
}
    
class SendClientMessageTask implements Runnable {
    private Socket socket;
        
    SendClientMessageTask (Socket socket) {
        this.socket = socket;
    }
        
    @Override
    public void run() {
        Scanner input = new Scanner(System.in);
        try {
            ObjectOutputStream toClient = new ObjectOutputStream(socket.getOutputStream());
            while(true) {
                String message = input.nextLine();
                toClient.writeObject(message);
            }
        } 
        catch(IOException e1) {}
    }
}
