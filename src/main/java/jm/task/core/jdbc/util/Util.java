package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USER = "root1";
    private static final String PASS = "root1";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL,USER,PASS);
        } catch (SQLException e) {
            System.out.println("Не удалось подключиться к бд");
        }
        return conn;
    }
}
