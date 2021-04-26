package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSesstionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String sql = "CREATE TABLE IF NOT EXISTS USERS(" +
                    "Id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "Name VARCHAR(45), " +
                    "LastName VARCHAR(45), " +
                    "Age INT)";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSesstionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String sql = "DROP TABLE IF EXISTS USERS";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSesstionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(new User(name, lastName, age));
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSesstionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(id);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Session session = Util.getSesstionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            userList = (List<User>) session.createCriteria(User.class).list();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            transaction.rollback();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSesstionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String sql = "TRUNCATE TABLE USERS";
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }
}
