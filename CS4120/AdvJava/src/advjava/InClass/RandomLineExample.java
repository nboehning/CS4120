package advjava.InClass;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * @author Nathan Boehning
 * Date: 10/21/2017
 * Purpose: Draw 100 random lines of random colors/positions
 */
public class RandomLineExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane canvas = new Pane();
        
        for(int i = 0; i < 100000; i++) {
            Line line = new Line();
            line.setStartX((int) (Math.random() * 1080));
            line.setStartY((int) (Math.random() * 720));
            line.setEndX((int) (Math.random() * 1080));
            line.setEndY((int) (Math.random() * 720));
            
            line.setStroke(new Color(Math.random(), Math.random(), Math.random(), 1.0));
            //line.setStrokeWidth((int) (Math.random() * 10) + 5);
            
            canvas.getChildren().add(line);
        }
        
        Scene scene = new Scene(canvas, 1080, 720);
        primaryStage.setTitle("Random Lines");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String args[]) {
        launch(args);
    }
}
