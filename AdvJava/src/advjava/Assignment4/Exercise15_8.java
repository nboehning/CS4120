package advjava.Assignment4;

/**
 * @author Nathan Boehning
 * Date: 11/9/2017
 * Purpose: Display position of mouse when the mouse is clicked and mouse is 
 *          clicked and held.
 *          Left mouse click is for the click
 *          Right mouse click is for the hold
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise15_8 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Text mousePosText = new Text();
        mousePosText.setFont(new Font(20));
        mousePosText.setX(10);
        mousePosText.setY(50);
        pane.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                // left mouse button clicked
                mousePosText.setText("(" + event.getSceneX() + ", " + event.getSceneY() + ")");
                
            }
        });
        pane.setOnMousePressed((MouseEvent event) -> {
            if(event.getButton() == MouseButton.SECONDARY) {
                // left mouse button clicked
                mousePosText.setText("(" + event.getSceneX() + ", " + event.getSceneY() + ")");
                
            }
        });
        
        pane.setOnMouseReleased((MouseEvent event) -> {
            if(event.getButton() == MouseButton.SECONDARY) {
                // left mouse button clicked
                mousePosText.setText("");
                
            }
        });
        
        
        pane.getChildren().add(mousePosText);
        
        // Create scene and add the parent pane
        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setTitle("Exercise 15_8");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
} // End of class Exercise15_8
