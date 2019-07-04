package ru.netrax.services;

import ru.netrax.dao.UserDao;
import ru.netrax.dao.UserDaoImp;
import ru.netrax.models.User;

public class DBService {
    private UserDao usersDao = new UserDaoImp();

    public DBService() {
    }

    public User findUser(long id) {
        return usersDao.findById(id);
    }

    public void saveUser(User user) {
        usersDao.save(user);
    }
}
