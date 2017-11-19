package advjava.InClass;

/**
 * @author Nathan Boehning
 * Date: 10/14/2017
 * Purpose: Server code for in class example
 */

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        
        ServerSocket serverRadiusSocket = new ServerSocket(8000);
        ServerSocket serverBMISocket = new ServerSocket(8001);
        
        while(true) {
            System.out.println("Server waiting for client");

            Socket socketRadius = serverRadiusSocket.accept();
            Socket socketBMI = serverBMISocket.accept();

            System.out.println("Client connected");

            if(socketBMI.isConnected()) {
                DataInputStream fromClient = new DataInputStream(socketBMI.getInputStream());
                DataOutputStream toClient = new DataOutputStream(socketBMI.getOutputStream());

                double height = fromClient.readDouble();

                System.out.println("Height Receieved: " + height);
                
                double weight = fromClient.readDouble();

                System.out.println("Weight Receieved: " + weight);

                double bmi = (weight / (height * height)) * 703;

                toClient.writeDouble(bmi);

                System.out.println("Sent bmi: " + bmi);
            } 
            if(socketRadius.isConnected()) {
                DataInputStream fromClient = new DataInputStream(socketRadius.getInputStream());
                DataOutputStream toClient = new DataOutputStream(socketRadius.getOutputStream());

                double radius = fromClient.readDouble();

                System.out.println("Radius Receieved: " + radius);

                double area = radius * radius * Math.PI;

                toClient.writeDouble(area);

                System.out.println("Sent area: " + area);
            }
        }
    }
}
