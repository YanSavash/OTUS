package ru.netrax.services;

import ru.netrax.models.User;

public interface DBServiceInterface {

    public User findUser(long id);

    public void saveUser(User t);

    public void deleteUser(long id);

    public void closeTimer() throws Exception;
}
