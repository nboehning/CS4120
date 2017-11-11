package advjava.Assignment4;

/**
 * @author Nathan Boehning
 * Date: 11/11/2017
 * Purpose: Create 2 user changeable draggable rectangles that detects if they're
 *          intersecting or not.
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

public class Exercise16_9 extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        Text intersectText = new Text();
        
        
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        
        Pane rectPane = new Pane();
        
        Rectangle rect1 = new Rectangle();
        Rectangle rect2 = new Rectangle();
        
        Button btnRedrawRect = new Button("Redraw Rectangles");
        
        rect1.setX(100);
        rect1.setY(40);
        rect1.setWidth(50);
        rect1.setHeight(10);
        rect1.setFill(Color.WHITE);
        rect1.setStroke(Color.BLACK);
        
        rect2.setX(300);
        rect2.setY(50);
        rect2.setWidth(100);
        rect2.setHeight(30);
        rect2.setFill(Color.WHITE);
        rect2.setStroke(Color.BLACK);
        
        
        rectPane.getChildren().addAll(rect1, rect2);        
        
        intersectText.setText("Two rectangles intersect? " + (isIntersect(rect1, rect2) ? "Yes" : "No"));
        
        // All of the information box stuff
        HBox infoBox = new HBox();
        
        VBox rect1Info = new VBox();
        Text r1text = new Text("Rectangle 1 Info");
        
        HBox r1xbox = new HBox();
        Label r1XLabel = new Label("X:");
        TextField r1XInfo = new TextField(String.valueOf(rect1.getX()));
        r1xbox.getChildren().addAll(r1XLabel, r1XInfo);
        r1xbox.setSpacing(40);
        
        HBox r1ybox = new HBox();
        Label r1YLabel = new Label("Y:");
        TextField r1YInfo = new TextField(String.valueOf(rect1.getY()));
        r1ybox.getChildren().addAll(r1YLabel, r1YInfo);
        r1ybox.setSpacing(40);
        
        HBox r1wbox = new HBox();
        Label r1WLabel = new Label("Width:");
        TextField r1WInfo = new TextField(String.valueOf(rect1.getWidth()));
        r1wbox.getChildren().addAll(r1WLabel, r1WInfo);
        r1wbox.setSpacing(15);
        
        HBox r1hbox = new HBox();
        Label r1HLabel = new Label("Height:");
        TextField r1HInfo = new TextField(String.valueOf(rect1.getHeight()));
        r1hbox.getChildren().addAll(r1HLabel, r1HInfo);
        r1hbox.setSpacing(10);
        
        rect1Info.getChildren().addAll(r1text, r1xbox, r1ybox, r1wbox, r1hbox);
        rect1Info.setSpacing(5);
        rect1Info.setAlignment(Pos.CENTER);       
        
        VBox rect2Info = new VBox();
        Text r2text = new Text("Rectangle 2 Info");
        
        HBox r2xbox = new HBox();
        Label r2XLabel = new Label("X:");
        TextField r2XInfo = new TextField(String.valueOf(rect2.getX()));
        r2xbox.getChildren().addAll(r2XLabel, r2XInfo);
        r2xbox.setSpacing(40);
        
        HBox r2ybox = new HBox();
        Label r2YLabel = new Label("Y:");
        TextField r2YInfo = new TextField(String.valueOf(rect2.getY()));
        r2ybox.getChildren().addAll(r2YLabel, r2YInfo);
        r2ybox.setSpacing(40);
        
        HBox r2wbox = new HBox();
        Label r2WLabel = new Label("Width:");
        TextField r2WInfo = new TextField(String.valueOf(rect2.getWidth()));
        r2wbox.getChildren().addAll(r2WLabel, r2WInfo);
        r2wbox.setSpacing(15);
        
        HBox r2hbox = new HBox();
        Label r2HLabel = new Label("Height:");
        TextField r2HInfo = new TextField(String.valueOf(rect2.getHeight()));
        r2hbox.getChildren().addAll(r2HLabel, r2HInfo);
        r2hbox.setSpacing(10);
        
        rect2Info.getChildren().addAll(r2text, r2xbox, r2ybox, r2wbox, r2hbox);
        rect2Info.setSpacing(5);
        rect2Info.setAlignment(Pos.CENTER);
        
        infoBox.getChildren().addAll(rect1Info, rect2Info);
        infoBox.setSpacing(25);
        infoBox.setAlignment(Pos.CENTER);
        
        root.getChildren().addAll(intersectText, rectPane, infoBox, btnRedrawRect);
        root.setSpacing(5);
        
        // End of rect fields
        
        // Event Handlers
        rect1.setOnMouseDragged(e -> {
            rect1.setX(e.getX() - (rect1.getWidth() / 2));
            r1XInfo.setText(String.valueOf(rect1.getX()));
            rect1.setY(e.getY() - (rect1.getHeight() / 2));
            r1YInfo.setText(String.valueOf(rect1.getY()));
            intersectText.setText("Two rectangles intersect? " + (isIntersect(rect1, rect2) ? "Yes" : "No"));
        });
        
        rect2.setOnMouseDragged(e -> {
            rect2.setX(e.getX() - (rect2.getWidth() / 2));
            r2XInfo.setText(String.valueOf(rect2.getX()));
            rect2.setY(e.getY() - (rect2.getHeight() / 2));
            r2YInfo.setText(String.valueOf(rect2.getY()));
            intersectText.setText("Two rectangles intersect? " + (isIntersect(rect1, rect2) ? "Yes" : "No"));
        });
        
        btnRedrawRect.setOnAction(e -> {
            rect1.setX(Double.parseDouble(r1XInfo.getText()));
            rect1.setY(Double.parseDouble(r1YInfo.getText()));
            rect1.setWidth(Double.parseDouble(r1WInfo.getText()));
            rect1.setHeight(Double.parseDouble(r1HInfo.getText()));
            
            rect2.setX(Double.parseDouble(r2XInfo.getText()));
            rect2.setY(Double.parseDouble(r2YInfo.getText()));
            rect2.setWidth(Double.parseDouble(r2WInfo.getText()));
            rect2.setHeight(Double.parseDouble(r2HInfo.getText()));
        });
        
        Scene scene = new Scene(root, 500, 400);
        primaryStage.setTitle("Exercise14_11");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private boolean isIntersect(Rectangle rect1, Rectangle rect2) {
         if(rect1.contains(rect2.getX(), rect2.getY())
                || rect1.contains(rect2.getX() + rect2.getWidth(), rect2.getY())
                || rect1.contains(rect2.getX() + rect2.getWidth(), rect2.getY() + rect2.getHeight())
                || rect1.contains(rect2.getX(), rect2.getY() + rect2.getHeight()))
             return true;
         else
             return false;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
} // End of class Exercise16_9
