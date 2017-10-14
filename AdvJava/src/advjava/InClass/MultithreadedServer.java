package advjava.InClass;

/**
 * @author Nathan Boehning
 * Date: 
 * Purpose: 
 */

import java.io.*;
import java.net.*;

public class MultithreadedServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverRadiusSocket = new ServerSocket(8000);
        ServerSocket serverBmiSocket = new ServerSocket(8001);
        int numClient = 0;
        
        while(true) {
            System.out.println("Waiting for client");
            
            Socket socket = serverBmiSocket.accept();
            numClient++;
            
            System.out.println("Client " + numClient + " connected.");
            
            HandleBmiClientTask task = new HandleBmiClientTask(socket, numClient);
            Thread thread = new Thread(task);
            thread.start();
        }
    }
}

class HandleBmiClientTask implements Runnable {
        private Socket socket;
    private int clientID;
    
    HandleBmiClientTask(Socket sock, int clientId) {
        socket = sock;
        clientID = clientId;
    }
    
    @Override
    public void run() {
        try {
            DataInputStream fromClient = new DataInputStream(socket.getInputStream());
            DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
            
            while(true) {
            double height = fromClient.readDouble();

            System.out.println("Height Receieved From Client " + clientID + ": " + height);
                
            double weight = fromClient.readDouble();

            System.out.println("Weight Receieved From Client " + clientID + ": " + weight);

            double bmi = (weight / (height * height)) * 703;

            toClient.writeDouble(bmi);

            System.out.println("Sent bmi to client + " + clientID + ": " + bmi);
            }
        
        } catch(IOException e) {}
    }
}

class HandleRadiusClientTask implements Runnable {
    private Socket socket;
    private int clientID;
    
    HandleRadiusClientTask(Socket sock, int clientId) {
        socket = sock;
        clientID = clientId;
    }
    
    @Override
    public void run() {
        try {
            DataInputStream fromClient = new DataInputStream(socket.getInputStream());
            DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
            
            while(true) {
                System.out.println("Waiting for radius from Client " + clientID);
                
                double radius = fromClient.readDouble();
                
                System.out.println("Received radius " + radius + " from client " + clientID);
                
                double area = radius * radius * Math.PI;
                
                toClient.writeDouble(area);
                
                System.out.println("Sent area " + area + " to client " + clientID);
            }
        } catch(IOException e) {}
    }
}
