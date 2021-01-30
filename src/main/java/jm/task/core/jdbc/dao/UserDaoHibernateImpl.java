package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import java.util.List;

import static jm.task.core.jdbc.util.Util.*;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = getSession();
        String sql = "CREATE TABLE IF NOT EXISTS user (Id INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(50), LastName VARCHAR(50), Age SMALLINT )";
        org.hibernate.Transaction tx = session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        try {
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        session.getSessionFactory().close();
    }

    @Override
    public void dropUsersTable() {
        Session session = getSession();
        String sql = "DROP TABLE IF EXISTS user";
        org.hibernate.Transaction tx = session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        try {
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        session.getSessionFactory().close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        org.hibernate.Transaction tx = null;
        Session session;
        try {
            session = Util.getSession();
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            session.close();
            session.getSessionFactory().close();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = getSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("delete User where id = :id");
        q.setParameter("id", id).executeUpdate();
        try {
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        session.getSessionFactory().close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        List<User> user = null;
        try {
            session = getSession();
            Query query = session.createQuery("From User");
            user = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        session.getSessionFactory().close();
        return user;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        try {
            session = getSession();
            org.hibernate.Transaction tx = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        session.getSessionFactory().close();
    }
}
