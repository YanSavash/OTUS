package ru.netrax.services;

import ru.netrax.dao.UserDao;
import ru.netrax.models.User;

import java.util.List;

public class DBService<T> implements DBServiceInterface<T> {
    private UserDao usersDao;

    public DBService(UserDao usersDao) {
        this.usersDao = usersDao;
    }

    public T findUser(long id) {
        return (T) usersDao.findById(id);
    }

    public void saveUser(T user) {
        usersDao.save((User) user);
    }

    @Override
    public List<User> getAllUsers() {
        return usersDao.getAllUsers();
    }
}