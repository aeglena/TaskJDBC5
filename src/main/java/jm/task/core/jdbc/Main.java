package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userService = new UserDaoJDBCImpl();
        userService.createUsersTable();
        String[] name = {"Ivan", "Petr", "Sidor", "Svetlana"};
        String[] lastname = {"Ivanov", "Petrov", "Sidorov", "Svetlichnaya"};
        byte[] age = {40, 5, 15, 25};
        for (int i = 0; i < 3; i++) {
            userService.saveUser(name[i], lastname[i], age[i]);
        }
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
