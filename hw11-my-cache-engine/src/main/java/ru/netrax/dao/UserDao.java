package ru.netrax.dao;

import ru.netrax.models.User;

public interface UserDao {
    User findById(long id);

    void save(User user);

    public void delete(long id);
}
