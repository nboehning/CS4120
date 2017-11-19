package advjava.Assignment4;

/**
 * @author Nathan Boehning
 * Date: 11/8/2017
 * Purpose: Allows user to input dimensions and center coordinate of two
 *          rectangles. Shows the drawn rectangles and tells whether they are
 *          overlapping, contained, or not touching.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import java.util.Scanner;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Exercise14_23 extends Application {
    private static double rec1x;
    private static double rec1y;
    private static double rec1width;
    private static double rec1height;
    
    private static double rec2x;
    private static double rec2y;
    private static double rec2width;
    private static double rec2height;
    
    @Override
    public void start(Stage primaryStage) {
        String textToDisplay;
        Pane canvas = new Pane();
        
        // Create the rectangle objects with the width and height set
        Rectangle rect1 = new Rectangle(rec1width, rec1height);
        Rectangle rect2 = new Rectangle(rec2width, rec2height);
        
        // Set the positions of the rectangles, offsetting because
        // center points are given, not upper-left corner
        rect1.setX(rec1x - (rec1width / 2));
        rect1.setY(rec1y - (rec1height / 2));
        
        rect2.setX(rec2x - (rec2width / 2));
        rect2.setY(rec2y - (rec2height / 2));
        
        
        rect1.setFill(Color.WHITE);
        rect1.setStroke(Color.BLACK);
        rect2.setFill(Color.WHITE);
        rect2.setStroke(Color.BLACK);
        
        canvas.getChildren().add(rect1);
        canvas.getChildren().add(rect2);
        
        // Check if rect1 contains rect2 by seeing if both the upper left and lower
        // right points of rect2 are in rect1.
        if(rect1.contains(rec2x - (rec2width / 2), rec2y - (rec2height / 2))
           && rect1.contains(rec2x + (rec2width / 2), rec2y + (rec2height / 2))) {
            // rect1 does contain rect2
            textToDisplay = "Rectangle 1 contains rectangle 2";
        }       
        // Do the inverse to see if rect2 overlaps or contains rect1
        else if(rect2.contains(rec1x - (rec1width / 2), rec1y - (rec1height / 2))
           && rect2.contains(rec1x + (rec1width / 2), rec1y + (rec1height / 2))) {
            // rect2 does contain rect1
            
            textToDisplay = "Rectangle 2 contains rectangle 1";
        }
        // Check if rect1 contains any corner of rect2 to determine if 
        // they are overlapping. Don't need to check the other way
        else if(rect1.contains(rec2x - (rec2width / 2), rec2y - (rec2height / 2))
             || rect1.contains(rec2x + (rec2width / 2), rec2y + (rec2height / 2))
             || rect1.contains(rec2x + (rec2width / 2), rec2y - (rec2height / 2))
             || rect1.contains(rec2x - (rec2width / 2), rec2y + (rec2height / 2))) {
            // rect1 does contain at least one of the corners
            
            textToDisplay = "The rectangles overlap";
        } 
        // Otherwise the two rectangles are not overlapping or containing each other
        else {
            textToDisplay = "The rectangles do not overlap";
        }
        
        Text textBox = new Text(10, 50, textToDisplay);
        textBox.setFont(new Font(20));
        canvas.getChildren().add(textBox);
        
        
        Scene scene = new Scene(canvas, 500, 500);
        primaryStage.setTitle("Exercise14_23");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        // Get the values for rectangle 1
        System.out.print("Please enter the x-coord of rectangle 1: ");
        rec1x = in.nextDouble();
        
        System.out.print("Please enter the y-coord of rectangle 1: ");
        rec1y = in.nextDouble();
        
        System.out.print("Please enter the width of rectangle 1: ");
        rec1width = in.nextDouble();
        
        System.out.print("Please enter the height of rectangle 1: ");
        rec1height = in.nextDouble();
        
        
        // Get the values for rectangle 2
        System.out.print("Please enter the x-coord of rectangle 2: ");
        rec2x = in.nextDouble();
        
        System.out.print("Please enter the y-coord of rectangle 2: ");
        rec2y = in.nextDouble();
        
        System.out.print("Please enter the width of rectangle 2: ");
        rec2width = in.nextDouble();
        
        System.out.print("Please enter the height of rectangle 2: ");
        rec2height = in.nextDouble();
        
        // Launch the application once values are read in and stored
        launch(args);
    }
}
