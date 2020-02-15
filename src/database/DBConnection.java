package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/project_red_002?useSSL=false";
    private String user = "indu";
    private String pass = "1234";

    public Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(url,user,pass);
        return connection;
    }

}
