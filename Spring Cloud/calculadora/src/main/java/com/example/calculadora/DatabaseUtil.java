package com.example.calculadora;

import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {
    public static Connection getDatabaseConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mariadb://turistar-database-01.cohcvyy7lspu.us-east-1.rds.amazonaws.com:3306/MicroServices", "admin", "Welcome123#");
        return con;
    }

    public static void createLog(Connection con, String service, String userName, String result){
        Date date = new Date(System.currentTimeMillis());
        String query = "insert into logs(user,invocation_date,description,operation)" +
                "values (?,?,?,?)";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1,userName);
            preparedStmt.setDate(2, new java.sql.Date(date.getTime()));
            preparedStmt.setString(3,result);
            preparedStmt.setString(4,service);
            preparedStmt.execute();
            preparedStmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<LogMessage> getLogs(Connection con){
        List<LogMessage> logs = null;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from logs;");
            logs = new ArrayList<>();
            while(rs.next()){
                String userName = rs.getString(2);
                Date date = rs.getDate(3);
                String result = rs.getString(4);
                String operation = rs.getString(5);
                logs.add(new LogMessage(userName,date,result,operation));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }

}
