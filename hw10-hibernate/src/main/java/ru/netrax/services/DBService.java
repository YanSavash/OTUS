package ru.netrax.services;

import ru.netrax.dao.UserDao;
import ru.netrax.models.User;

public class DBService {
    private UserDao usersDao;

    public DBService(UserDao usersDao) {
        this.usersDao = usersDao;
    }

    public User findUser(long id) {
        return usersDao.findById(id);
    }

    public void saveUser(User user) {
        usersDao.save(user);
    }
}