package advjava.InClass;

/**
 * @author Nathan Boehning
 * Date: 10/14/2017
 * Purpose: Client code for in class example
 */

import java.util.Scanner;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Socket socket;
        DataInputStream fromServer;
        DataOutputStream toServer;
        while(true){
            System.out.print("Get 'bmi' or 'radius'?: ");
            String choice = input.next();
            switch(choice) {
                case "bmi":
                    socket = new Socket("localhost", 8001);
                    
                    System.out.println("Connect to server on port 8001");
                    
                    fromServer = new DataInputStream(socket.getInputStream());
                    toServer = new DataOutputStream(socket.getOutputStream());
                    
                    System.out.print("Enter height in inches: ");
                    double height = input.nextDouble();
                    
                    toServer.writeDouble(height);
                    
                    System.out.println("Sent height to server");
                    
                    System.out.print("Enter weight in lbs: ");
                    double weight = input.nextDouble();
                    
                    toServer.writeDouble(weight);
                    
                    double bmi = fromServer.readDouble();
                    
                    System.out.println("bmi received from server: " + bmi);
                    
                    socket.close();
                    break;
                case "radius":
                    socket = new Socket("153.91.107.99", 8000);
        
                    System.out.println("Connected to server on port 8000.");
        
                    fromServer = new DataInputStream(socket.getInputStream());
                    toServer = new DataOutputStream(socket.getOutputStream());
                    
                    System.out.print("Enter a radius: ");
                    double radius = input.nextDouble();

                    toServer.writeDouble(radius);

                    System.out.println("Sent radius, waiting for area");

                    double area = fromServer.readDouble();

                    System.out.println("Area Receieved from Server: " + area);
                    
                    socket.close();
                    break;
                default:
                    System.out.println("Invalid choie entered");
            }
        }
      
    }
}
