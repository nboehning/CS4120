package advjava.Assignment4;

/**
 * @author Nathan Boehning
 * Date: 11/8/2017
 * Purpose: Program that draws the x^2 line
 */

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

public class Exercise14_18 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Pane canvas = new Pane();
        
        Polyline x2line = new Polyline();
        ObservableList<Double> list = x2line.getPoints();
        double scaleFactor = 0.0125;
        for(int x = -100; x <= 100; x++) {
            list.add(x + 200.0);
            list.add(150 - (scaleFactor * x * x));
        }
        

        
        Line lineX = new Line();
        lineX.setStartX(0);
        lineX.setStartY(150);
        
        lineX.setEndX(400);
        lineX.setEndY(150);
        
        Line lineY = new Line();
        
        lineY.setStartX(200);
        lineY.setStartY(0);
        
        lineY.setEndX(200);
        lineY.setEndY(200);
        
        canvas.getChildren().add(x2line);
        canvas.getChildren().add(lineX);
        canvas.getChildren().add(lineY);
        
        Scene scene = new Scene(canvas, 400, 200);
        primaryStage.setTitle("Exercise14_18");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
