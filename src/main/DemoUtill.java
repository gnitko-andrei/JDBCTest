package main;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DemoUtill {
    static void printResultSet(ResultSet resultSet) throws SQLException {
        System.out.println("Retrieving data from database...");
        System.out.println("\nDevelopers:");
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String speciality = resultSet.getString("speciality");
            int salary = resultSet.getInt("salary");

            System.out.println("id: " + id);
            System.out.println("name: " + name);
            System.out.println("speciality: " + speciality);
            System.out.println("salary: " + salary);
            System.out.println("\n======================================================\n");
        }
    }
}
