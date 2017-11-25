package advjava.FinalPart1;

/**
 * @author Nathan Boehning
 * Date: 11/25/2017
 * Purpose: Client to connect to server and provide front end to user to create
 *          a username and password
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Q2_AccountClient extends Application {      
    private static LinkedBlockingQueue<String> sendMessages = new LinkedBlockingQueue<String>();
    private static LinkedBlockingQueue<String> receiveMessages = new LinkedBlockingQueue<String>();
    
    public static void main(String[] args) throws IOException, ClassNotFoundException  {    
        ConnectToServer();
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        // Holds everything
        BorderPane root = new BorderPane();
        // Open application to have user input their username to join the chatroom
        HBox usernameBox = new HBox();
        HBox passwordBox = new HBox();
        HBox interBox = new HBox();
        VBox holder = new VBox();
        
        Label usernameText = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setText("New User");
        usernameField.setMaxWidth(100);
        usernameBox.getChildren().addAll(usernameText, usernameField);
        usernameBox.setSpacing(10);
        usernameBox.setAlignment(Pos.CENTER);
        
        Label passwordText = new Label("Password:");
        TextField passwordField = new PasswordField();
        passwordField.setMaxWidth(100);
        passwordBox.getChildren().addAll(passwordText, passwordField);
        passwordBox.setSpacing(10);
        passwordBox.setAlignment(Pos.CENTER);
        
        
        Button regButton = new Button("Register");
        Text responseText = new Text();
        interBox.getChildren().addAll(regButton, responseText);
        interBox.setSpacing(10);
        interBox.setAlignment(Pos.CENTER);
        
        holder.getChildren().addAll(usernameBox, passwordBox, interBox);
        
        
        root.setCenter(holder);        
        
        regButton.setOnAction(e -> {
            if(usernameField.getText() != null && passwordField.getText() != null) {
                                  
                    sendMessages.add(usernameField.getText());
                    sendMessages.add(passwordField.getText());
                    
                try {
                    responseText.setText(receiveMessages.take());
                } 
                catch (InterruptedException ex) {}
            }
        });
        
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Register Account");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private static void ConnectToServer() throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 8000);
        
        ReceiveServerMessageTask receiveTask = new ReceiveServerMessageTask(socket);
        Thread thread = new Thread(receiveTask);
        thread.start();

        SendServerMessageTask sendTask = new SendServerMessageTask(socket);
        thread = new Thread(sendTask);
        thread.start();
    }
    
    static class ReceiveServerMessageTask implements Runnable {
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
                        receiveMessages.add(message);
                    }
                    catch(ClassNotFoundException e2) {}
                }
            } 
            catch(IOException e1) {}
        }
    }

    static class SendServerMessageTask implements Runnable {
        private Socket socket;

        SendServerMessageTask (Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
                while(true) {
                    String message = sendMessages.take();
                    toServer.writeObject(message);
                }
            } 
            catch(IOException | InterruptedException  e1) {}
        }
    }
} // End of class Q2_AccountClient
