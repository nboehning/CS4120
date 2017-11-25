package advjava.Midterm2;

/**
 * @author Nathan Boehning
 * Date: 11/16/2017
 * Purpose: Create an agar.io clone using javafx
 */

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Q4_Mulario extends Application {

    private ArrayList<Circle> circles = new ArrayList<Circle>();
    Pane circlePane = new Pane();
    Circle playerCircle = new Circle(250, 245, 10);
    
    @Override
    public void start(Stage primaryStage) {
        // Initializing a few things
        circlePane.setStyle("-fx-background-color: white");
        Text playerSizeText = new Text(Integer.toString((int)playerCircle.getRadius()));
        playerSizeText.setFont(new Font((int) playerCircle.getRadius()));
        playerSizeText.setX(playerCircle.getCenterX() - 5);
        playerSizeText.setY(playerCircle.getCenterY() + 5);
        
        
        BorderPane root = new BorderPane();
        HBox optionsPane = new HBox();
        optionsPane.setSpacing(20);
        optionsPane.setAlignment(Pos.CENTER);
        
        // Circle stuff
                
        for(int i = 0; i < 50; i++) {
            Circle circle = new Circle();
            circle.setRadius(Math.random() * 10 + 5);
            circle.setCenterX((int) (Math.random() * 500));
            circle.setCenterY((int) (Math.random() * 450));
            
            circle.setFill(new Color(Math.random(), Math.random(), Math.random(), 1.0));
            
            circles.add(circle);
        }
        
        circlePane.getChildren().add(playerCircle);
        circlePane.getChildren().addAll(circles);
        circlePane.getChildren().add(playerSizeText);
        
        // Player circle controls
        playerCircle.setOnKeyPressed((KeyEvent e) -> {
           if(null != e.getCode()) switch (e.getCode()) {
                case UP:
                    playerCircle.setCenterY(playerCircle.getCenterY() - 5);
                    playerSizeText.setY(playerSizeText.getY() - 5);
                    break;
                case DOWN:
                    playerCircle.setCenterY(playerCircle.getCenterY() + 5);                    
                    playerSizeText.setY(playerSizeText.getY() + 5);
                    break;
                case RIGHT:
                    playerCircle.setCenterX(playerCircle.getCenterX() + 5);
                    playerSizeText.setX(playerSizeText.getX() + 5);
                    break;
                case LEFT:
                    playerCircle.setCenterX(playerCircle.getCenterX() - 5);                    
                    playerSizeText.setX(playerSizeText.getX() - 5);
                    break;
                default:
                    break;
            }
           
            CheckCollision();
            playerSizeText.setFont(new Font((int) playerCircle.getRadius()));
            playerSizeText.setText(Integer.toString((int)playerCircle.getRadius()));
        });
        
        
        // Options things
        root.setMargin(optionsPane, new Insets(10, 0, 10, 0));
        
        Label redLabel = new Label("Red:");
        Label greenLabel = new Label("Green:");
        Label blueLabel = new Label("Blue:");
        Label sizeLabel = new Label("Show Size:");
        
        // Set intial values of the players circle color
        Color tempC = (Color) playerCircle.getFill();
        playerSizeText.setFill(tempC.invert());
        
        TextField redField = new TextField();
        redField.setMaxWidth(40);
        redField.setFocusTraversable(false);
        redField.setText(Integer.toString((int)tempC.getRed()));
        // Make sure they can only input numbers, hard limit on 0 to 255
        redField.textProperty().addListener((observable, oldVal, newVal) -> {
            try {
                int newNum = Integer.parseInt(newVal);
                if(newNum < 0)
                    newNum = 0;
                else if(newNum > 255)
                    newNum = 255;
               
                redField.setText(Integer.toString(newNum));
            }
            catch(NumberFormatException e){
                redField.setText(oldVal);
            }
        });
        
        TextField greenField = new TextField();
        greenField.setMaxWidth(40);
        greenField.setFocusTraversable(false);
        greenField.setText(Integer.toString((int)tempC.getGreen()));
        // Make sure they can only input numbers, hard limit on 0 to 255
        greenField.textProperty().addListener((observable, oldVal, newVal) -> {
            try {
                int newNum = Integer.parseInt(newVal);
                if(newNum < 0)
                    newNum = 0;
                else if(newNum > 255)
                    newNum = 255;
               
                greenField.setText(Integer.toString(newNum));
            }
            catch(NumberFormatException e){
                greenField.setText(oldVal);
            }
        });
        
        TextField blueField = new TextField();
        blueField.setMaxWidth(40);
        blueField.setFocusTraversable(false);
        blueField.setText(Integer.toString((int)tempC.getBlue()));
        // Make sure they can only input numbers, hard limit on 0 to 255
        blueField.textProperty().addListener((observable, oldVal, newVal) -> {
            try {
                int newNum = Integer.parseInt(newVal);
                if(newNum < 0)
                    newNum = 0;
                else if(newNum > 255)
                    newNum = 255;
               
                blueField.setText(Integer.toString(newNum));
            }
            catch(NumberFormatException e){
                blueField.setText(oldVal);
            }
        });
        
        redField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER && !redField.getText().isEmpty()) {
                Color tempColor = Color.rgb(Integer.parseInt(redField.getText()), Integer.parseInt(greenField.getText()), Integer.parseInt(blueField.getText()));
                playerCircle.setFill(tempColor);    
                playerSizeText.setFill(tempColor.invert()); 
                playerCircle.requestFocus();           
            }
        });
        
        greenField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER && !greenField.getText().isEmpty()) {
                Color tempColor = Color.rgb(Integer.parseInt(redField.getText()), Integer.parseInt(greenField.getText()), Integer.parseInt(blueField.getText()));
                playerCircle.setFill(tempColor);          
                playerSizeText.setFill(tempColor.invert()); 
                playerCircle.requestFocus();     
            }
        });
        
        blueField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER && !blueField.getText().isEmpty()) {
                Color tempColor = Color.rgb(Integer.parseInt(redField.getText()), Integer.parseInt(greenField.getText()), Integer.parseInt(blueField.getText()));
                playerCircle.setFill(tempColor);
                playerSizeText.setFill(tempColor.invert());
                playerCircle.requestFocus();
            }
        });
        
        CheckBox showSize = new CheckBox(); 
        showSize.setFocusTraversable(false);
        playerSizeText.setVisible(showSize.isSelected());
        showSize.setOnAction(e -> {
            playerSizeText.setVisible(showSize.isSelected());
        });
        
        
        optionsPane.getChildren().addAll(redLabel, redField, greenLabel, greenField, blueLabel, blueField, sizeLabel, showSize);
        root.setCenter(circlePane);
        root.setBottom(optionsPane);
        
        
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Mulario");
        primaryStage.setScene(scene);
        primaryStage.show();
        playerCircle.requestFocus();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    private void CheckCollision() {
        for(int i = 0; i < circles.size(); i++) {
            if(playerCircle.getBoundsInParent().intersects(circles.get(i).getBoundsInParent())) {
                if(playerCircle.getRadius() > circles.get(i).getRadius()) {
                    circlePane.getChildren().remove(circles.get(i));
                    circles.remove(i);
                    playerCircle.setRadius(playerCircle.getRadius() * 1.05);    
                }
            }
        }
    }
} // End of class Q4_Mulario
