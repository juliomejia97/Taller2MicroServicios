package co.javeriana.ws.rest.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getDatabaseConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mariadb://turistar-database-01.cohcvyy7lspu.us-east-1.rds.amazonaws.com:3306/MicroServices","admin","Welcome123#");
        return con;
    }
}
