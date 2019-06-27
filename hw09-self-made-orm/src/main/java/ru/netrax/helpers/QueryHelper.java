package ru.netrax.helpers;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static ru.netrax.helpers.ReflectionHelper.getObjectFields;
import static ru.netrax.helpers.ReflectionHelper.getStringKey;

public class QueryHelper {
    public static String createSqlCreate(Object obj) {
        Field[] fields = getObjectFields(obj);
        StringBuilder sqlCreate = new StringBuilder("insert into " + obj.getClass().getSimpleName() + "(");
        for (int i = 1; i < fields.length; i++)
            if (i < fields.length - 1)
                sqlCreate.append(fields[i].getName()).append(",");
            else sqlCreate.append(fields[i].getName()).append(") values (");
        for (int i = 1; i < fields.length; i++)
            if (i < fields.length - 1)
                sqlCreate.append("?,");
            else sqlCreate.append("?)");
        return sqlCreate.toString();
    }

    public static String createSqlUpdate(Object obj) throws IllegalAccessException {
        Field[] fields = getObjectFields(obj);
        StringBuilder sqlUpdate = new StringBuilder("update " + obj.getClass().getSimpleName() + " set ");
        for (int i = 1; i < fields.length; i++)
            if (i < fields.length - 1)
                sqlUpdate.append(fields[i].getName()).append(" = ?,");
            else sqlUpdate.append(fields[i].getName()).append(" = ? where ").append(getStringKey(obj)).append(" = ?");
        return sqlUpdate.toString();
    }

    public static <T> String createSqlSelect(Class<T> clazz) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Constructor<T> constructor = clazz.getConstructor();
        Optional<T> obj = Optional.of(constructor.newInstance());
        return "select * from " + obj.get().getClass().getSimpleName() + " where " + getStringKey(obj.get()) + " = ?";
    }
}
