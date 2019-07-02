package ru.netrax.helpers;

import ru.netrax.annotation.Key;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ReflectionHelper {

    public static <T> Field[] getObjectFields(Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields)
            field.setAccessible(true);
        return fields;
    }

    public static Field getKeyFieldName(Field[] fields) {
        for (Field field : fields)
            if (field.isAnnotationPresent(Key.class))
                return field;
        return null;
    }

    public static <T> T setObjectFields(Class<T> clazz, ResultSet rs, Field[] objFields) throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Constructor<T> constructor = clazz.getConstructor();
        Optional<T> obj = Optional.of(constructor.newInstance());
        for (Field field : objFields)
            field.set(obj.get(), rs.getObject(field.getName()));
        return obj.isPresent() ? obj.get() : null;
    }
}