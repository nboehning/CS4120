/**
 * @author Nathan Boehning
 * Date: 11/16/2017
 * Purpose: Create the main menu for pac man game. Have options of joining
 *          or hosting a game.
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashSet;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class MainMenu extends Application {
    
    double width = 1000;
    double height = 1000;
    
    @Override
    public void start(Stage primaryStage) {  
        // <editor-fold defaultstate="collapsed" desc=" Basic Inits ">
        
        // Load fonts
        Font btnFont = Font.loadFont(getClass().getResourceAsStream("/fonts/slkscr.ttf"), 30);        
        Font titleFont = Font.loadFont(getClass().getResourceAsStream("/fonts/slkscr.ttf"), 60);

        // Set styles for hovering over buttons as well as idle
        String idleButtonStyle = new String("-fx-border-color: blue; -fx-background-color: black; -fx-text-fill: white;");
        String hoverButtonStyle = new String("-fx-border-color: white; -fx-background-color: black; -fx-text-fill: yellow");
        String textStyle = new String("-fx-background-color: black; -fx-text-fill: white;");

        // Define base pane
        BorderPane root = new BorderPane();        
        root.setStyle("-fx-background-color: black");

        // Create pane to hold various menus
        StackPane menu = new StackPane();

        // Styling rect
        Rectangle borderRect = new Rectangle();
        borderRect.setHeight(500);
        borderRect.setWidth(650);
        borderRect.setStroke(Color.BLUE);

// </editor-fold>                

        //<editor-fold defaultstate="collapsed" desc="Main Menu">

        // Pane for the main menu
        VBox mainMenuHolder = new VBox();
        mainMenuHolder.setAlignment(Pos.CENTER);
        mainMenuHolder.setSpacing(50);

        // Text fields and buttons for main menu
        Text titleText = new Text("Pac-Man Versus");
        titleText.setFont(titleFont);
        titleText.setStroke(Color.WHITE);
        titleText.setFill(Color.WHITE);

        Button btnJoinGameMenu = new Button("Join Game");
        btnJoinGameMenu.setFont(btnFont);
        btnJoinGameMenu.setStyle(idleButtonStyle);
        btnJoinGameMenu.setOnMouseEntered(e -> btnJoinGameMenu.setStyle(hoverButtonStyle));
        btnJoinGameMenu.setOnMouseExited(e -> btnJoinGameMenu.setStyle(idleButtonStyle));

        Button btnHostGame = new Button("Host Game");
        btnHostGame.setFont(btnFont);
        btnHostGame.setStyle(idleButtonStyle);
        btnHostGame.setOnMouseEntered(e -> btnHostGame.setStyle(hoverButtonStyle));
        btnHostGame.setOnMouseExited(e -> btnHostGame.setStyle(idleButtonStyle));

        // Initial population of the menu
        mainMenuHolder.getChildren().addAll(titleText, btnHostGame, btnJoinGameMenu);
        menu.getChildren().addAll(borderRect, mainMenuHolder);
        root.setCenter(menu);
        //</editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Join Menu">
        // Pane for the join menu        
        VBox joinMenuHolder = new VBox();
        joinMenuHolder.setAlignment(Pos.CENTER);
        joinMenuHolder.setSpacing(70);

        // Buttons, fields, labels for join menu
        Text joinMenuTitleText = new Text("Join Game");
        joinMenuTitleText.setFont(titleFont);
        joinMenuTitleText.setStroke(Color.WHITE);
        joinMenuTitleText.setFill(Color.WHITE);
        
        Label ipAddress = new Label(" Host Address:");
        ipAddress.setFont(btnFont);
        ipAddress.setStyle(textStyle);
        
        TextField ipAddressField = new TextField("");
        ipAddressField.setFont(btnFont);
        ipAddressField.setStyle(idleButtonStyle);
        ipAddressField.setMaxWidth(325);
        
        HBox ipBox = new HBox();
        ipBox.getChildren().addAll(ipAddress, ipAddressField);
        ipBox.setSpacing(10);
        ipBox.setAlignment(Pos.CENTER);        
        
        Button btnReturnToMain = new Button("Main Menu");
        btnReturnToMain.setFont(btnFont);
        btnReturnToMain.setStyle(idleButtonStyle);        
        btnReturnToMain.setOnMouseEntered(e -> btnReturnToMain.setStyle(hoverButtonStyle));        
        btnReturnToMain.setOnMouseExited(e -> btnReturnToMain.setStyle(idleButtonStyle));
        
        Button btnJoinGame = new Button("Join");
        btnJoinGame.setMinWidth(215);
        btnJoinGame.setFont(btnFont);
        btnJoinGame.setStyle(idleButtonStyle);        
        btnJoinGame.setOnMouseEntered(e -> btnJoinGame.setStyle(hoverButtonStyle));        
        btnJoinGame.setOnMouseExited(e -> btnJoinGame.setStyle(idleButtonStyle));
        
        HBox joinBtnBox = new HBox();
        joinBtnBox.getChildren().addAll(btnReturnToMain, btnJoinGame);
        joinBtnBox.setSpacing(150);
        joinBtnBox.setAlignment(Pos.CENTER);        
        
        joinMenuHolder.getChildren().addAll(joinMenuTitleText, ipBox, joinBtnBox);

// </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Buttons">
        // Button functions
        btnJoinGameMenu.setOnAction(e -> {
            menu.getChildren().remove(mainMenuHolder);
            menu.getChildren().add(joinMenuHolder);
            
        });
        
        btnReturnToMain.setOnAction(e -> {
            menu.getChildren().remove(joinMenuHolder);
            menu.getChildren().add(mainMenuHolder);
            
        });
        
        btnHostGame.setOnAction(e -> {
            
        });
        
        btnJoinGame.setOnAction(e -> {
            
        });
        // </editor-fold>
                
        // Display the menu
        Scene scene = new Scene(root, width, height);
        primaryStage.setTitle("Pac-Man: Versus");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
} // End of class MainMenu
