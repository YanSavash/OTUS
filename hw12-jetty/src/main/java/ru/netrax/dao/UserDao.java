package ru.netrax.dao;

import ru.netrax.models.User;

import java.util.List;

public interface UserDao {
    User findById(long id);

    void save(User user);

    List<User> getAllUsers();
}
