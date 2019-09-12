package main;

import java.sql.*;

import static main.DemoUtill.printResultSet;

public class PreparedStatementDemo {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/testDB";

    static final String USER_NAME = "root";
    static final String PASSWORD = "1111";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("Registering JDBC driver...");
        Class.forName(JDBC_DRIVER);
        System.out.println("Creating connection...");
        Connection connection = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);

        String SQL = "SELECT * FROM developers";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        System.out.println("Initial developers table content:");
        ResultSet resultSet = preparedStatement.executeQuery(SQL);
        printResultSet(resultSet);

        SQL = "UPDATE developers SET salary = ? WHERE speciality = ?";
        System.out.println("Creating statement...");
        System.out.println("Executing SQL query...");

        preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, 5000);
        preparedStatement.setString(2, "java");

        System.out.println("Rows impacted: " + preparedStatement.executeUpdate());

        System.out.println("Final developers table content:");
        SQL = "SELECT * FROM developers";
        resultSet = preparedStatement.executeQuery(SQL);
        printResultSet(resultSet);

        System.out.println("Closing connection and releasing resources...");
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
