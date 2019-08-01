package ru.netrax.services;

import org.springframework.stereotype.Component;
import ru.netrax.dao.UserRepository;
import ru.netrax.dao.UserRepositoryImp;
import ru.netrax.models.User;

import java.util.List;

@Component
public class DBService<T> implements DBServiceInterface<T> {
    private UserRepository usersDao = new UserRepositoryImp();

    public T findUser(long id) {
        return (T) usersDao.findById(id);
    }

    public void saveUser(T user) {
        usersDao.save((User) user);
    }

    public List<User> getAllUsers() {
        return usersDao.getAllUsers();
    }
}