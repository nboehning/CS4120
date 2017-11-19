package advjava.Assignment4;

/**
 * @author Nathan Boehning
 * Date: 11/10/2017
 * Purpose: Draw a fixed polygon and show when the mouse moves in and out of the
 *          polygon
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise15_14 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Text mousePosText = new Text();
        mousePosText.setFont(new Font(10));
        mousePosText.setX(10);
        mousePosText.setY(50);
        
        Polygon shape = new Polygon();
        shape.getPoints().addAll(new Double[]{
           40.0, 20.0,
           70.0, 40.0,
           60.0, 80.0,
           45.0, 45.0,
           20.0, 60.0
        });
        shape.setFill(Color.WHITE);
        shape.setStroke(Color.BLACK);
        
        pane.setOnMouseMoved((MouseEvent event) -> {
            if(shape.contains(event.getSceneX(), event.getSceneY()))
                mousePosText.setText("Mouse is inside polygon");
            else
                mousePosText.setText("Mouse is outside polygon");
        });
      
        pane.getChildren().add(shape);
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
} // End of class Exercise15_14
