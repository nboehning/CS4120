package advjava.InClass;

/**
 * @author Nathan Boehning
 * Date: 
 * Purpose: 
 */

import java.util.Scanner;
import java.io.*;
import java.net.*;

public class InstantMessageClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        Socket socket = new Socket("localhost", 8000);
        
        System.out.println("Connected to a server");
        
        ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
        
        while(true) {
            System.out.print("Enter a message: ");
            String message = input.nextLine();
            toServer.writeObject(message);
            
            message = (String)fromServer.readObject();
            System.out.println("Server: " + message);
        }
    }
}
