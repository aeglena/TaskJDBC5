package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSession().openSession();
        String sql = "CREATE TABLE IF NOT EXISTS user (Id INT PRIMARY KEY AUTO_INCREMENT, FirstName VARCHAR(50), LastName VARCHAR(50), Age SMALLINT )";
        org.hibernate.Transaction tx = session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        //query.executeUpdate();
        try {
            tx.commit();
        }  catch (Exception e) {
            e.printStackTrace();
        }


        session.close();
        Util.getSession().close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSession().openSession();
        String sql = "DROP TABLE IF EXISTS user";
        org.hibernate.Transaction tx = session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();

        try {
            tx.commit();
        }  catch (Exception e) {
            e.printStackTrace();
        }


        session.close();
        Util.getSession().close();
    }

   @Override
    public void saveUser(String name, String lastName, byte age) {
       org.hibernate.Transaction tx = null;
       try {
        Session session = Util.getSession().openSession();
        tx = session.beginTransaction();
           User user = new User();
           user.setName(name);
           user.setLastName(lastName);
           user.setAge(age);
           session.persist(user);
           //session.save(new User("Sid", "sig", (byte)12));
           //System.out.println(user);
           session.flush();
               tx.commit();
           } catch (Exception e) {
               e.printStackTrace();
           if (tx != null) {
               tx.rollback();
           }
           } finally {
               //session.close();
           }           //Util.getSession().close();
       }


    @Override
    public void removeUserById(long id) {
        Session session = Util.getSession().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        User user = (User)session.get(User.class, id);
        System.out.println(user);
        session.delete(user);
        //Query query = session.createQuery("DELETE FROM users WHERE id = :Id");
        //query.setParameter("Id", id);
        try {
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


        session.close();
        Util.getSession().close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> user = (List<User>) Util.getSession().openSession().createQuery("From User").list();

        return user;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM user");
        try {
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        Util.getSession().close();
    }
}
