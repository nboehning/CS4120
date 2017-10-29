package advjava.InClass;

/**
 * @author Nathan Boehning
 * Date: 10/21/2017
 * Purpose: Playing with Java FX in class
 */

import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXExample extends Application {
    @Override
    public void start(Stage primaryStage){
        Button btOK = new Button("OK");
        Scene scene = new Scene(btOK, 200, 250);
        primaryStage.setTitle("MyJavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String args[]) {
        launch(args);
    }
}
