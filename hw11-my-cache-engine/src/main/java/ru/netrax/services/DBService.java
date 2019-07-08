package ru.netrax.services;

import ru.netrax.cache.MyCache;
import ru.netrax.dao.UserDao;
import ru.netrax.models.User;

public class DBService<T> implements DBServiceInterface<T> {
    private UserDao usersDao;
    MyCache<Long, T> myCache = new MyCache<>(5, 0, 0, true);

    public DBService(UserDao usersDao) {
        this.usersDao = usersDao;
    }

    public T findUser(long id) {
        T str = myCache.get(id);
        return str;
    }

    public void saveUser(T user) {
        usersDao.save((User) user);
        myCache.put(((User) user).getId(), user);
    }

    public void deleteUser(long id) {
        System.out.println("delete from DB: " + findUser(id));
        usersDao.delete(id);
        System.out.println("delete from my cache: " + findUser(id));
        myCache.remove(id);
        myCache.dispose();
    }
}