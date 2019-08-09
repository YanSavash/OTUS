package ru.netrax.db;

import ru.netrax.messageSystem.Addressee;
import ru.netrax.models.User;

import java.util.List;

public interface DBServiceAddresse<T> extends Addressee {
    void init();

    int getUserId(String name);

    T findUser(long id);

    void saveUser(T t);

    List<User> getAllUsers();
}
