package advjava.Assignment4;

/**
 * @author Nathan Boehning
 * Date: 11/9/2017
 * Purpose: Program that calculates the future value of an investment at a given
 *          interest rate for a specified number of years
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
public class Exercise15_5 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        VBox textContainer = new VBox();
        VBox fieldContainer = new VBox();
        HBox container = new HBox();
        
        Text investAmtText = new Text("Investment Amount:");
        Text numYearsText = new Text("Number of Years:");
        Text interestRateText = new Text("Annual Interest Rate:");
        Text futureValText = new Text("Future Value:");
        
        TextField investAmtField = new TextField();
        TextField numYearsField = new TextField();
        TextField interestRateField = new TextField();
        TextField futureValField = new TextField();
        
        Button btnCalc = new Button("Calculate");
        
        btnCalc.setOnAction(e -> {
            Double invest = Double.valueOf(investAmtField.getText());
            Integer years = Integer.valueOf(numYearsField.getText());
            Double rate = Double.valueOf(interestRateField.getText());
            
            Double futureVal = invest * Math.pow((1 + ((rate/100) / 12)), years * 12);
            futureValField.setText(futureVal.toString());
        });
        
        textContainer.getChildren().addAll(investAmtText, numYearsText, interestRateText, futureValText);
        textContainer.setSpacing(13);
        
        fieldContainer.getChildren().addAll(investAmtField, numYearsField, interestRateField, futureValField, btnCalc);
        fieldContainer.setAlignment(Pos.BASELINE_RIGHT);
        container.getChildren().addAll(textContainer, fieldContainer);
        Scene scene = new Scene(container, 200, 200);
        primaryStage.setTitle("Exercise 15_5");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
} // End of class Exercise15_5
