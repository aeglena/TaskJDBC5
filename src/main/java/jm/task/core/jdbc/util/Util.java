package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    final String DATABASE_URL = "jdbc:mysql://localhost:3306/ex1";
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String USER = "root";
    final String PASSWORD = "35ьшфт";

    public Connection con() {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            if (!(connection.isClosed())) {
                System.out.println("OK");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Нет необходимого драйвера.");
        } catch (SQLException e) {
            System.out.println("Проблема с SQL");
        }
        return connection;
    }
}