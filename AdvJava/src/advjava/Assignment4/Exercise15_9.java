package advjava.Assignment4;

/**
 * @author Nathan Boehning
 * Date: 11/10/2017
 * Purpose: Draw line segments in the direction of the arrow key that you press.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise15_9 extends Application {
    
    Pane pane = new Pane();
    double width = 200;
    double height = 200;
    double x = width/2;
    double y = height/2;
    
    @Override
    public void start(Stage primaryStage) {
        
        pane.setOnKeyPressed(e -> {
           switch(e.getCode()) {
                case UP:
                    pane.getChildren().add(new Line(x, y, x, y-15));
                    y -= 15;
                   break;
                case DOWN:
                    pane.getChildren().add(new Line(x, y, x, y+15));
                    y += 15;
                    break;
                case LEFT:
                    pane.getChildren().add(new Line(x, y, x-15, y));
                    x -= 15;
                    break;
                case RIGHT:
                    pane.getChildren().add(new Line(x, y, x+15, y));
                    x += 15;
                    break;
           } 
        });
        
        
        // Create scene and add the parent pane
        Scene scene = new Scene(pane, width, height);
        primaryStage.setTitle("Exercise 15_9");
        primaryStage.setScene(scene);
        primaryStage.show();
        pane.requestFocus();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
            
} // End of class Exercise15_9
