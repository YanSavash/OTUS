package ru.netrax.executor;

import ru.netrax.annotation.Key;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

class ReflectionHelperCreate {
    static String sqlCreate;
    static String sqlUpdate;
    static String sqlSelect;
    static Field[] fields;
    static String id;

    static void getObjectFields(Object obj) throws IllegalAccessException {
        fields = obj.getClass().getDeclaredFields();

        for (Field field : fields)
            field.setAccessible(true);

        for (Field field : fields)
            if (field.isAnnotationPresent(Key.class)) {
                id = field.getName();
                System.out.println(id);
                sqlCreate = "insert into " + obj.getClass().getSimpleName() +
                        "(" + fields[1].getName() +
                        "," + fields[2].getName() +
                        ") values (?, ?)";
                sqlUpdate = "update " + obj.getClass().getSimpleName() +
                        " set " + fields[1].getName() + " = ?," +
                        fields[2].getName() + " = ? where " +
                        id + " = ?";
                sqlSelect = "select * from " + obj.getClass().getSimpleName() + " where " + field.getName() + " = ?";
            }
    }

    static <T> T setObjectFields(Class<T> clazz, ResultSet rs, Field[] fields) throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Constructor<T> constructor = clazz.getConstructor();
        Optional<T> obj = Optional.of(constructor.newInstance());
        Field[] objFields = obj.get().getClass().getDeclaredFields();
        for (Field field : objFields)
            field.setAccessible(true);
        objFields[0].setInt(obj.get(), rs.getInt(fields[0].getName()));
        objFields[1].set(obj.get(), rs.getString(fields[1].getName()));
        objFields[2].setInt(obj.get(), rs.getInt(fields[2].getName()));
        return obj.isPresent() ? obj.get() : null;
    }
}