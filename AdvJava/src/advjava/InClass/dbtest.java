package advjava.InClass;

/**
 * @author Nathan Boehning
 * Date: 11/11/2017
 * Purpose: Test to ensure the java->mysql settings are correct
 */

import java.sql.*;

public class dbtest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded.");
        
        Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/javabook?user=scott&password=tiger");
        System.out.println("Database connected");
        
        Statement statement = conn.createStatement();
        
        ResultSet resultSet = statement.executeQuery
                ("select firstName, mi, lastName from Student where lastName"
                + " = 'Smith'");
        
        while(resultSet.next()) {
            System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2)
             + "\t" + resultSet.getString(3));
        }
        
        conn.close();
    }
}
