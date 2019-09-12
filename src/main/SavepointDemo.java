package main;

import java.sql.*;

import static main.DemoUtill.printResultSet;

public class SavepointDemo {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/testDB";

    static final String USER = "root";
    static final String PASSWORD = "1111";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        String SQL = "SELECT * FROM developers";
        ResultSet resultSet = statement.executeQuery(SQL);
        printResultSet(resultSet);

        System.out.println("\n===========================\n");
        System.out.println("Creating savepoint...");
        Savepoint savepoint = connection.setSavepoint("SavepointOne");

        SQL = "INSERT INTO developers VALUES (6, 'John', 'C#', 2200)";
        statement.executeUpdate(SQL);
//        connection.commit();
        SQL = "INSERT INTO developers VALUES (7, 'Ron', 'Ruby', 1900)";
        statement.executeUpdate(SQL);

        connection.commit();

        SQL = "SELECT * FROM developers";
        resultSet = statement.executeQuery(SQL);
        printResultSet(resultSet);

        System.out.println("Closing connection and releasing resources...");
        resultSet.close();
        statement.close();
        connection.close();
    }
}
