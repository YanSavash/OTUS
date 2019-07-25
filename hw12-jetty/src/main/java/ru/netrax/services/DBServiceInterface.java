package ru.netrax.services;

import ru.netrax.models.User;

import java.util.List;

public interface DBServiceInterface<T> {

    public T findUser(long id);

    public void saveUser(T t);

    public List<User> getAllUsers();
}
