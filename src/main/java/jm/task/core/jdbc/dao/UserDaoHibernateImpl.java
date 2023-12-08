package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory = Util.getConnectionHibernate();
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            String sqlCommand = "CREATE TABLE IF NOT EXISTS Users (id INT PRIMARY KEY auto_increment, name VARCHAR(255), lastName VARCHAR(255), age TINYINT)";
            session.createSQLQuery(sqlCommand).executeUpdate();
            transaction.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            String sqlCommand = "DROP TABLE IF EXISTS users";
            session.createSQLQuery(sqlCommand).executeUpdate();
            transaction.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println(name + " добавлен в базу данных");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            List<User> users = session.createQuery("FROM User", User.class).list();
            transaction.commit();
            return users;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.createQuery("Delete FROM User",User.class).executeUpdate();
            transaction.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
