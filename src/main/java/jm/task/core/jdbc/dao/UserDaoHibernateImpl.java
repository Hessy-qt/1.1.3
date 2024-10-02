package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;


import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            String sql = "CREATE TABLE if not exists users(id int primary key auto_increment," +
                    " name varchar(100), lastname varchar(100), " +
                    "age int)";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
        }

    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            String sql = "drop table if exists users";
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("from User").list();
            session.getTransaction().commit();
            return users;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
