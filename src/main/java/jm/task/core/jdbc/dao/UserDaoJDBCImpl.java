package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        Util util = new Util();
        Connection connection = util.con();
        try {
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS users (Id INT PRIMARY KEY AUTO_INCREMENT, FirstName VARCHAR(50), LastName VARCHAR(50), Age SMALLINT )");
        } catch (SQLException e) {
            System.out.println("Проблема с SQL");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public void dropUsersTable() {
        Util util = new Util();
        Connection connection = util.con();
        try {
            connection.createStatement().executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            System.out.println("Проблема с SQL");
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Util util = new Util();
        Connection connection = util.con();
        try {
            connection.createStatement().executeUpdate(String.format("INSERT INTO users (FirstName, LastName, Age) VALUES ('%s', '%s', %d)", name, lastName, age));
        } catch (SQLException e) {
            System.out.println("Проблема с SQL");
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public void removeUserById(long id) {
        Util util = new Util();
        Connection connection = util.con();
        try {
            connection.createStatement().executeUpdate(String.format("DELETE FROM users WHERE Id = %d", id));
        } catch (SQLException e) {
            System.out.println("Проблема с SQL");
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public List<User> getAllUsers() {
        Util util = new Util();
        Connection connection = util.con();
        List<User> listuser = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                int age = resultSet.getInt("Age");
                listuser.add(new User(name, lastName, (byte) age));
                System.out.printf("%d. %s %s %d \n", id, name, lastName, age);
            }
        } catch (SQLException e) {
            System.out.println("Проблема с SQL");
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        System.out.println(listuser);
        return listuser;
    }

    public void cleanUsersTable() {
        Util util = new Util();
        Connection connection = util.con();
        try {
            connection.createStatement().executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            System.out.println("Проблема с SQL");
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
