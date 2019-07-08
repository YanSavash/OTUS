package ru.netrax.services;

public interface DBServiceInterface<T> {

    public T findUser(long id);

    public void saveUser(T t);
}
