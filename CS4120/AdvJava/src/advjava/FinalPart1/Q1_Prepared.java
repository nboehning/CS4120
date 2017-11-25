package advjava.FinalPart1;

/**
 * @author Nathan Boehning
 * Date: 11/24/2017
 * Purpose: Completes the first question in the final part 1
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Q1_Prepared {

    public static void main(String[] args) throws SQLException, ClassNotFoundException  {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded.");
        
        Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/midterm?user=scott&password=tiger");
        System.out.println("Database connected");
        
        PreparedStatement preparedStatement = conn.prepareStatement("insert into watchlist(first_name, last_name, money)"
                + "values(?,?,?)");
                
        long startTime = System.currentTimeMillis();
        
        for(int i = 0; i < 10000; i++) {
            preparedStatement.setString(1, generateString());
            preparedStatement.setString(2, generateString());
            preparedStatement.setInt(3, (int) (Math.random() * 999998 + 1));
            preparedStatement.execute();
        }
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("Time to insert data into tables: " + (endTime - startTime)
        + " milliseconds"); 
        
        conn.close();
    }
    
    private static String generateString() {
        Random rand = new Random();
        
        int strLen = rand.nextInt(12 - 3) + 3;
        char[] strHolder = new char[strLen];
        for(int j = 0; j < strLen; j++) 
            strHolder[j] = (char)(rand.nextInt(26) + 97);
        
        return new String(strHolder);
    }
} // End of class Q1_Prepared
