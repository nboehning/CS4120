package advjava.Assignment5;

/**
 * @author Nathan Boehning
 * Date: 11/19/2017
 * Purpose: Allows a user to access a dictionary db and view information
 *          about a book the select
 */

import java.sql.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DictionaryApp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        VBox holder = new VBox();
        
        VBox wordAndDef = new VBox();
        Text wordText = new Text();
        Text defText = new Text();
        
        HBox inBox = new HBox();
        TextField wordField = new TextField();
        Button searchButton = new Button("Look Up");
        
        wordAndDef.getChildren().addAll(wordText, defText);
        
        inBox.getChildren().addAll(wordField, searchButton);
        inBox.setAlignment(Pos.BASELINE_CENTER);
        holder.getChildren().addAll(wordAndDef, inBox);
        holder.setSpacing(30);
        holder.setAlignment(Pos.BASELINE_CENTER);
        
        root.getChildren().add(holder);
        
        searchButton.setOnAction(e -> {
            if(wordField.getText() != "") {
                try {
                    String def = makeQuery(wordField.getText());
                    wordText.setText("Word: " + wordField.getText());
                    defText.setText("Definition: " + def);
                } catch (SQLException | ClassNotFoundException ex) {}
            }
        });
        
        Scene scene = new Scene(root, 210, 100);
        primaryStage.setTitle("Dictionary Application");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        launch(args);
    }
    
    public static String makeQuery(String wordToFind) throws SQLException, ClassNotFoundException {
        String toReturn;
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded.");
        
        Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/dictionary?user=scott&password=tiger");
        System.out.println("Database connected");
        
        Statement statement = conn.createStatement();
        
        ResultSet resultSet = statement.executeQuery
                ("select definition from entry where word"
                + " = '" + wordToFind + "'");
        
        resultSet.next();
        toReturn = resultSet.getString(1);
        conn.close();
        
        return toReturn;
        
    }
    
} // End of class DictionaryApp
