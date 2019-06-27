package ru.netrax.helpers;

import ru.netrax.annotation.Key;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ReflectionHelper {

    public static Field[] getObjectFields(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields)
            field.setAccessible(true);
        return fields;
    }

    public static String getStringKey(Object obj) throws IllegalAccessException {
        String id = "";
        for (Field field : getObjectFields(obj))
            if (field.isAnnotationPresent(Key.class)) {
                id = field.getName();
            }
        return id;
    }

    public static long getIntegerKey(Object obj) throws IllegalAccessException {
        long id = 0;
        for (Field field : getObjectFields(obj))
            if (field.isAnnotationPresent(Key.class))
                id = (long) field.get(obj);
        return id;
    }

    public static <T> T setObjectFields(Class<T> clazz, ResultSet rs) throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Constructor<T> constructor = clazz.getConstructor();
        Optional<T> obj = Optional.of(constructor.newInstance());
        Field[] objFields = getObjectFields(obj.get());
        for (Field field : objFields)
            field.set(obj.get(), rs.getObject(field.getName()));
        return obj.isPresent() ? obj.get() : null;
    }
}