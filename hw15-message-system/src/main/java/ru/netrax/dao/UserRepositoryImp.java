package ru.netrax.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.netrax.models.User;
import ru.netrax.utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImp implements UserRepository {
    private SessionFactory sessionFactory = HibernateSessionFactoryUtil.
            getSessionFactory("hibernate.cfg.xml", User.class);

    @Override
    public User findById(long id) {
        User user;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
        }
        return user;
    }

    @Override
    public void save(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List list = new ArrayList();
        try (Session session = sessionFactory.openSession()) {
            long count = ((Long) session.createQuery("select count(*) from User").uniqueResult());

            User user;
            for (long i = 1; i <= count; i++) {
                Transaction transaction = session.beginTransaction();
                user = (session.get(User.class, i));
                transaction.commit();
                list.add(user);
            }
        }
        return list;
    }
}