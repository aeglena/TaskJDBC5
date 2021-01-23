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
            String SQL = "CREATE TABLE IF NOT EXISTS users (Id INT PRIMARY KEY AUTO_INCREMENT, FirstName VARCHAR(50), LastName VARCHAR(50), Age SMALLINT )";
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
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
            String SQL = "DROP TABLE IF EXISTS users";
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
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
            Statement statement = null;
            String SQL = String.format("INSERT INTO users (FirstName, LastName, Age) VALUES ('%s', '%s', %d)", name, lastName, age);
            statement = connection.createStatement();
            statement.executeUpdate(SQL);
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
            Statement statement = null;
            String SQL = String.format("DELETE FROM users WHERE Id = %d", id);
            statement = connection.createStatement();
            statement.executeUpdate(SQL);
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
            Statement statement = null;
            String SQL = "SELECT * FROM users";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
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
            Statement statement = null;
            String SQL = "DELETE FROM users";
            statement = connection.createStatement();
            statement.executeUpdate(SQL);
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
