package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.UserDao;
import lk.ijse.entity.User;
import lk.ijse.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean add(User entity) throws IOException {
//        System.out.println(entity);
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public String generateNewID() throws IOException {
        return null;
    }

    @Override
    public List<User> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM User");
        nativeQuery.addEntity(User.class);
        List<User> users = nativeQuery.list();


        transaction.commit();
        session.close();

        return users;
    }

    @Override
    public boolean update(User entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean delete(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        User user = new User();
        user.setUserName(id);
        session.remove(user);


        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public User search(String userName) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        User user = null;

        try {
            String hql = "FROM User WHERE userName = :userName";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("userName", userName);
            user = query.uniqueResult();


            if (user == null) {
                throw new IOException("User not found: " + userName);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e; // Re-throw the exception
        } finally {
            session.close();
        }

        return user;
    }


}
