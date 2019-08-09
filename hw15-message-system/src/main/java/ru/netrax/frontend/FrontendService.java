package ru.netrax.frontend;

import ru.netrax.messageSystem.Addressee;
import ru.netrax.models.User;

import java.util.List;

public interface FrontendService<T> extends Addressee {
    void init();

    void saveUser(T t);

    void getAllUsers(List<User> list);

    List<User> getList();
}
