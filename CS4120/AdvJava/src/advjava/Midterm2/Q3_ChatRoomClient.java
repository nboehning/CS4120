package advjava.Midterm2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;

/**
 * @author Nathan Boehning
 * Date: 11/16/2017
 * Purpose: Client component of a chat room application
 */
public class Q3_ChatRoomClient extends Application {
    
    private static LinkedBlockingQueue<String> sendMessages = new LinkedBlockingQueue<String>();
    private static LinkedBlockingQueue<String> receiveMessages = new LinkedBlockingQueue<String>();
    private String username;
    TextArea messageArea = new TextArea();
    
    public static void main(String[] args)  {    
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        // Holds everything
        BorderPane root = new BorderPane();
        // Open application to have user input their username to join the chatroom
        HBox usernameBox = new HBox();
        
        Label usernameText = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setText("New User");
        usernameField.setMaxWidth(100);
        Button joinBtn = new Button("Join Chatroom");
        usernameBox.getChildren().addAll(usernameText, usernameField, joinBtn);
        usernameBox.setSpacing(10);
        usernameBox.setAlignment(Pos.CENTER);
        
        root.setCenter(usernameBox);
        
        // Javafx stuff for the inside the chatroom
        messageArea.setWrapText(true);
        messageArea.setMaxWidth(250);
        messageArea.setMaxHeight(200);
        messageArea.setEditable(false);
        TextField sendField = new TextField();
        
        
        sendField.minWidth(400);
        Button sendBtn = new Button("Send");
        sendBtn.maxWidth(30);
        HBox sendArea = new HBox();
        sendArea.setAlignment(Pos.TOP_RIGHT);
        sendArea.getChildren().addAll(sendField, sendBtn);
        
        // User can enter their username either by pressing enter or clicking the button       
        usernameField.setOnKeyPressed(e -> {
            
           if(e.getCode() == KeyCode.ENTER) {
                if(usernameField.getText() != null) {
                    try {
                        ConnectToServer();
                    } 
                    catch (IOException | ClassNotFoundException ex) {}
               
                    root.setCenter(messageArea);
                    root.setBottom(sendArea);
                    username = usernameField.getText();
                    root.getChildren().remove(usernameBox);
                }
           }
        });
        
        joinBtn.setOnAction(e -> {
            if(usernameField.getText() != null) {
                    try {
                        ConnectToServer();
                    } 
                    catch (IOException | ClassNotFoundException ex) {}
               
                    root.setCenter(messageArea);
                    root.setBottom(sendArea);
                    username = usernameField.getText();
                    root.getChildren().remove(usernameBox);
                }
        });
        
        // User can send a message by either presseing enter or clicking the button
        sendField.setOnKeyPressed(e -> {
           if(e.getCode() == KeyCode.ENTER) {
               if(sendField.getText() != null) {
                   sendMessages.add(username + ": " + sendField.getText());
                   sendField.setText("");
               }
           }
        });
        
        sendBtn.setOnAction(e -> {
            if(sendField.getText() != null) {
                sendMessages.add(username + ": " + sendField.getText());
                sendField.setText("");
            }
        });
        
        // Thread to constantly check received messages and print them to the text area
        Thread read = new Thread() {
            @Override
            public void run() {
                while(true) {
                    try {
                        String newMessage = receiveMessages.take();
                        messageArea.appendText(newMessage + "\n");
                    } 
                    catch (InterruptedException ex) {}
                }
            }
        };
        
        // Start the thread
        read.start();
        
        
        root.setMargin(sendArea, new Insets(0, 25, 10, 0));
        
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("IM Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void ConnectToServer() throws IOException, ClassNotFoundException {
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
}