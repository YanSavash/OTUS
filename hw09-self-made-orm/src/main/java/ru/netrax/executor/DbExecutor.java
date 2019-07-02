package ru.netrax.executor;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface DbExecutor<T> {
    void create(T objectData) throws SQLException, IllegalAccessException;

    void update(T objectData) throws SQLException, IllegalAccessException;

    <T> T load(long id) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
