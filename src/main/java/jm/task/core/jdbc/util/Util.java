package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.mapping.MetadataSource;
import org.hibernate.metamodel.Metadata;
import org.hibernate.metamodel.MetadataSources;

import javax.imageio.spi.ServiceRegistry;
import java.lang.module.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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

    //private static SessionFactory sessionFactory;
    private static SessionFactory concreteSessionFactory;
    public static SessionFactory getSession() {
        if (concreteSessionFactory == null) {
            try {
                Properties prop = new Properties();
                prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/ex1");
                prop.setProperty("hibernate.connection.username", "root");
                prop.setProperty("hibernate.connection.password", "35ьшфт");
                prop.setProperty("dialect", "org.hibernate.dialect.MySQL5Dialect");

                prop.setProperty("hibernate.hbm2ddl.auto", "create");

                concreteSessionFactory = new org.hibernate.cfg.Configuration()
                        .addProperties(prop)
                        .addAnnotatedClass(User.class)
                       .buildSessionFactory();
                if(concreteSessionFactory.openSession().isConnected()) {
                System.out.println("ok");}


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return concreteSessionFactory;
    }
}