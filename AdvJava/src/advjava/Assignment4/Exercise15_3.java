package advjava.Assignment4;

/**
 * @author Nathan Boehning
 * Date: 11/9/2017
 * Purpose: Move a ball around inside a pane using buttons. It stops at the 
 *          edge of the pane
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class Exercise15_3 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create ball and ball pane
        Pane ballPane = new Pane();
        Circle ball = new Circle();
        ball.setCenterX(50);
        ball.setCenterY(50);
        ball.setRadius(20);
        ballPane.getChildren().add(ball);
        
        // Create the buttons and button pane
        HBox btnPane = new HBox(5);
        btnPane.setAlignment(Pos.CENTER);
        Button btnUp = new Button("Up");
        Button btnDown = new Button("Down");
        Button btnLeft = new Button("Left");
        Button btnRight = new Button("Right");
        btnPane.getChildren().addAll(btnUp, btnDown, btnLeft, btnRight);
        
        // Give the buttons their actions
        
        // Up button
        btnUp.setOnAction(e -> {
            if(ball.getCenterY() > 25)
                ball.setCenterY(ball.getCenterY() - 10);
        });

        // Down button
        btnDown.setOnAction(e -> {
            if(ball.getCenterY() < 140)
                ball.setCenterY(ball.getCenterY() + 10);
        });        

        // Left button
        btnLeft.setOnAction(e -> {
            if(ball.getCenterX() > 25)
                ball.setCenterX(ball.getCenterX() - 10);
        });        

        // Right button
        btnRight.setOnAction(e -> {
            if(ball.getCenterX() < 175)
                ball.setCenterX(ball.getCenterX() + 10);
        });
        
        //Combine the panes
        BorderPane pane = new BorderPane();
        pane.setCenter(ballPane);
        pane.setBottom(btnPane);
        
        // Create scene and add the parent pane
        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setTitle("Exercise 15_3");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
} // End of class Exercise15_3
