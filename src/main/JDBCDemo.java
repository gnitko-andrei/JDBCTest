package main;

import java.sql.*;

import static main.DemoUtill.printResultSet;

public class JDBCDemo {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/testDB";

    static final String USER = "root";
    static final String PASSWORD = "1111";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        System.out.println("Register JDBC Driver ...");

//        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Class.forName(JDBC_DRIVER);

        System.out.println("Create database connection ...");
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);


        System.out.println("Executing statement");
        statement = connection.createStatement();

        String sql = "SELECT * FROM developers;";

        ResultSet resultSet = statement.executeQuery(sql);

        printResultSet(resultSet);

        System.out.println("Closing connection and releasing resources...");
        resultSet.close();
        statement.close();
        connection.close();

    }

}
