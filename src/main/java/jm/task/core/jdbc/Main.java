package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userService = new UserDaoHibernateImpl();
        userService.createUsersTable();
        String[] name = {"Ivan", "Petr", "Sidor", "Svetlana"};
        String[] lastname = {"Ivanov1", "Petrov", "Sidorov", "Svetlichnaya"};
        byte[] age = {40, 5, 15, 25};
        for (int i = 0; i < 4; i++) {
            userService.saveUser(name[i], lastname[i], age[i]);
        }
        List<User> u = userService.getAllUsers();
        System.out.println(u);
        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
