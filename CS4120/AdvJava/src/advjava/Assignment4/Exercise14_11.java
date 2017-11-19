package advjava.Assignment4;

/**
 * @author Nathan Boehning
 * Date: 10/25/2017
 * Purpose: Draw a smiley face using JavaFX
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Exercise14_11 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane canvas = new Pane();
        
        Circle head = new Circle();
        head.setCenterX(100);
        head.setCenterY(100);
        head.setRadius(75);
        head.setFill(Color.WHITE);
        head.setStroke(Color.BLACK);
        canvas.getChildren().add(head);
        
        Polygon nose = new Polygon();
        nose.getPoints().addAll(new Double[] {
           100.0, 80.0,
           120.0, 120.0,
           80.0,  120.0 });
        nose.setFill(Color.WHITE);
        nose.setStroke(Color.BLACK);
        canvas.getChildren().add(nose);
        
        Ellipse leftEye = new Ellipse();
        leftEye.setCenterX(70);
        leftEye.setCenterY(70);
        leftEye.setRadiusX(20);
        leftEye.setRadiusY(15);
        leftEye.setFill(Color.WHITE);
        leftEye.setStroke(Color.BLACK);
        canvas.getChildren().add(leftEye);
        
        Circle leftIris = new Circle();
        leftIris.setCenterX(70);
        leftIris.setCenterY(70);
        leftIris.setRadius(11);
        canvas.getChildren().add(leftIris);
        
        Ellipse rightEye = new Ellipse();
        rightEye.setCenterX(130);
        rightEye.setCenterY(70);
        rightEye.setRadiusX(20);
        rightEye.setRadiusY(15);
        rightEye.setFill(Color.WHITE);
        rightEye.setStroke(Color.BLACK);
        canvas.getChildren().add(rightEye);
        
        Circle rightIris = new Circle();
        rightIris.setCenterX(130);
        rightIris.setCenterY(70);
        rightIris.setRadius(11);
        canvas.getChildren().add(rightIris);
        
        Arc mouth = new Arc();
        mouth.setCenterX(100);
        mouth.setCenterY(130);
        mouth.setRadiusX(45);
        mouth.setRadiusY(15);
        mouth.setStartAngle(0);
        mouth.setLength(-180);
        mouth.setType(ArcType.OPEN);
        mouth.setFill(Color.WHITE);
        mouth.setStroke(Color.BLACK);
        canvas.getChildren().add(mouth);
        
        Scene scene = new Scene(canvas, 200, 200);
        primaryStage.setTitle("Exercise14_11");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
