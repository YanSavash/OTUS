package ru.netrax.services;

import ru.netrax.cache.MyCache;
import ru.netrax.dao.UserDao;
import ru.netrax.models.User;

public class DBService implements DBServiceInterface {
    private UserDao usersDao;
    private MyCache<Long, User> myCache = new MyCache<>(3, 0, 0, true);

    public DBService(UserDao usersDao) {
        this.usersDao = usersDao;
    }

    public User findUser(long id) {
        User str = myCache.get(id);
        if (str == null)
            str = usersDao.findById(id);
        return str;
    }

    public void saveUser(User user) {
        usersDao.save((User) user);
        myCache.put(user.getId(), user);
    }

    public void deleteUser(long id) {
        System.out.println("delete from DB: " + findUser(id));
        usersDao.delete(id);
        System.out.println("delete from my cache: " + findUser(id));
        myCache.remove(id);
    }

    @Override
    public void disposeTimer() {
        myCache.dispose();
    }
}