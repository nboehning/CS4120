package advjava.Assignment4;

/**
 * @author Nathan Boehning
 * Date: 11/10/2017
 * Purpose: Giving the distance between two circles, one of which is draggable.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Exercise15_16 extends Application {
    
    Circle circle1 = new Circle(40, 40, 10);
    Circle circle2 = new Circle(120, 150, 10);
    Text distanceText = new Text();
    Line lineBetweenCircles = new Line();
    
    double x1 = circle1.getCenterX();
    double y1 = circle1.getCenterY();
    double x2 = circle2.getCenterX();
    double y2 = circle2.getCenterY();
    
    double centerX;
    double centerY;
    Double distance;

    
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();        
        
        circle1.setFill(Color.WHITE);
        circle1.setStroke(Color.BLACK);
                
        circle2.setFill(Color.WHITE);
        circle2.setStroke(Color.BLACK);
               
        distance = Math.sqrt(((y2-y1)*(y2-y1)) + ((x2-x1)*(x2-x1)));
        
        distanceText.setFont(new Font(10));
        
        lineBetweenCircles.setStartX( x1 - circle1.getRadius() * (x1-x2) / distance);
        lineBetweenCircles.setStartY( y1 - circle1.getRadius() * (y1-y2) / distance);
        lineBetweenCircles.setEndX( x2 + circle2.getRadius() * (x1-x2) / distance);
        lineBetweenCircles.setEndY( y2 + circle2.getRadius() * (y1-y2) / distance);
        
        centerX = (x1 + x2) / 2;
        centerY = (y1 + y2) / 2;
        
        distanceText.setText(distance.toString());
        distanceText.setX(centerX);
        distanceText.setY(centerY);
                
        circle1.setOnMouseDragged(event -> {
            double x = event.getX();
            double y = event.getY();
            
            circle1.setCenterX(x);
            circle1.setCenterY(y);
            
            x1 = circle1.getCenterX();
            y1 = circle1.getCenterY();
            
            centerX = (x1 + x2) / 2;
            centerY = (y1 + y2) / 2;
            
            distance = Math.sqrt(((y2-y1)*(y2-y1)) + ((x2-x1)*(x2-x1)));
        
            lineBetweenCircles.setStartX( x1 - circle1.getRadius() * (x1-x2) / distance);
            lineBetweenCircles.setStartY( y1 - circle1.getRadius() * (y1-y2) / distance);
            lineBetweenCircles.setEndX( x2 + circle2.getRadius() * (x1-x2) / distance);
            lineBetweenCircles.setEndY( y2 + circle2.getRadius() * (y1-y2) / distance);
            
            distanceText.setText(distance.toString());
            distanceText.setX(centerX);
            distanceText.setY(centerY);
        });
        
        circle2.setOnMouseDragged(event -> {
            double x = event.getX();
            double y = event.getY();
            circle2.setCenterX(x);
            circle2.setCenterY(y);
            
            x2 = circle2.getCenterX();
            y2 = circle2.getCenterY();
            
            centerX = (x1 + x2) / 2;
            centerY = (y1 + y2) / 2;
            
            distance = Math.sqrt(((y2-y1)*(y2-y1)) + ((x2-x1)*(x2-x1)));
        
            lineBetweenCircles.setStartX( x1 - circle1.getRadius() * (x1-x2) / distance);
            lineBetweenCircles.setStartY( y1 - circle1.getRadius() * (y1-y2) / distance);
            lineBetweenCircles.setEndX( x2 + circle2.getRadius() * (x1-x2) / distance);
            lineBetweenCircles.setEndY(y2 + circle2.getRadius() * (y1-y2) / distance);
            
            distanceText.setText(distance.toString());
            distanceText.setX(centerX);
            distanceText.setY(centerY);
        });
        
        pane.getChildren().addAll(circle1, circle2, lineBetweenCircles, distanceText);
        
        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setTitle("Exercise15_16");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
} // End of class Exercise15_16
