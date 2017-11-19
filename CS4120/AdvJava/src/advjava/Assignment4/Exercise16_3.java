package advjava.Assignment4;

/**
 * @author Nathan Boehning
 * Date: 
 * Purpose:
 */

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Exercise16_3 extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        Circle red = new Circle();
        red.setFill(Color.WHITE);
        red.setStroke(Color.BLACK);
        Circle yellow = new Circle();
        yellow.setFill(Color.WHITE);
        yellow.setStroke(Color.BLACK);
        Circle green = new Circle();
        green.setFill(Color.WHITE);
        green.setStroke(Color.BLACK);
        
        ToggleGroup lightGroup = new ToggleGroup();
        
        red.setRadius(10);
        yellow.setRadius(10);
        green.setRadius(10);
        
        RadioButton redRadio = new RadioButton("Red");
        redRadio.setToggleGroup(lightGroup);
        redRadio.setUserData("Red");
        
        RadioButton yellowRadio = new RadioButton("Yellow");
        yellowRadio.setToggleGroup(lightGroup);
        yellowRadio.setUserData("Yellow");
        
        RadioButton greenRadio = new RadioButton("Green");
        greenRadio.setToggleGroup(lightGroup);
        greenRadio.setUserData("Green");
        
        lightGroup.selectedToggleProperty().addListener(
                (ObservableValue<? extends Toggle> ov, Toggle old_toggle, 
                        Toggle new_toggle) -> {       
                    if(lightGroup.getSelectedToggle() != null) {
                        switch(lightGroup.getSelectedToggle().getUserData().toString()) {
                            case "Red":
                                red.setFill(Color.RED);
                                yellow.setFill(Color.WHITE);
                                green.setFill(Color.WHITE);
                                break;
                            case "Yellow":
                                red.setFill(Color.WHITE);
                                yellow.setFill(Color.YELLOW);
                                green.setFill(Color.WHITE);
                                break;
                            case "Green":
                                red.setFill(Color.WHITE);
                                yellow.setFill(Color.WHITE);
                                green.setFill(Color.GREEN);
                                break;
                        }
                    }
                    
        });
        
        VBox holder = new VBox();
        HBox radio = new HBox();
        
        holder.setAlignment(Pos.CENTER);
        radio.setAlignment(Pos.CENTER);
        
        holder.setSpacing(15);
        radio.setSpacing(10);
        
        radio.getChildren().addAll(greenRadio, yellowRadio, redRadio);
        holder.getChildren().addAll(red, yellow, green, radio);
        
        Scene scene = new Scene(holder, 200, 200);
        primaryStage.setTitle("Exercise16_3");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
} // End of class Exercise16_3
