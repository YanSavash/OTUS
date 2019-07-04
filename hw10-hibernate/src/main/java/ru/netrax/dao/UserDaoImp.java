package ru.netrax.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.netrax.models.User;
import ru.netrax.utils.HibernateSessionFactoryUtil;

public class UserDaoImp implements UserDao {
    private static SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    @Override
    public User findById(long id) {
        return sessionFactory.openSession().get(User.class, id);
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }
}
