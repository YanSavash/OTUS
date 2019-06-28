package ru.netrax.helpers;

import java.lang.reflect.Field;

public class QueryHelper {
    public static <T> String createSqlCreate(Class<T> clazz, Field[] fields) {
        StringBuilder sqlCreate = new StringBuilder("insert into " + clazz.getSimpleName() + "(");
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

    public static <T> String createSqlUpdate(Class<T> clazz, Field[] fields, Field keyField) {
        StringBuilder sqlUpdate = new StringBuilder("update " + clazz.getSimpleName() + " set ");
        for (int i = 1; i < fields.length; i++)
            if (i < fields.length - 1)
                sqlUpdate.append(fields[i].getName()).append(" = ?,");
            else sqlUpdate.append(fields[i].getName()).append(" = ? where ").append(keyField.getName()).append(" = ?");
        return sqlUpdate.toString();
    }

    public static <T> String createSqlSelect(Class<T> clazz, Field keyField) {
        return "select * from " + clazz.getSimpleName() + " where " + keyField.getName() + " = ?";
    }
}