package advjava.Assignment4;

/**
 * @author Nathan Boehning
 * Date: 11/10/2017
 * Purpose: Convert from miles to kilometers, or vice versa.
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Exercise16_4 extends Application {

    final double kiloInMile = 1.602307;
    
    @Override
    public void start(Stage primaryStage) {
        TextField mileField = new TextField();
        TextField kiloField = new TextField();
        Text mileText = new Text("Mile");
        Text kiloText = new Text("Kilo");
        
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.add(mileText, 0, 0);
        pane.add(mileField, 1, 0);
        pane.add(kiloText, 0, 1);
        pane.add(kiloField, 1, 1);
        
        mileField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                double miles = Double.parseDouble(mileField.getText());
                kiloField.setText(String.valueOf(miles * kiloInMile));
            }
        });
        
        kiloField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                double kilo = Double.parseDouble(kiloField.getText());
                mileField.setText(String.valueOf(kilo / kiloInMile));
            }
        });
        
        
        
        
        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setTitle("Exercise16_4");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
} // End of class Exercise16_4
