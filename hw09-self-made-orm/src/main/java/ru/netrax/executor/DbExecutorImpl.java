package ru.netrax.executor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

import static ru.netrax.helpers.QueryHelper.*;
import static ru.netrax.helpers.ReflectionHelper.*;

public class DbExecutorImpl<T> implements DbExecutor<T> {
    private final Connection connection;
    private final Class<T> clazz;
    private Field[] fields;
    private Field keyFiled;
    private String createSql;
    private String updateSql;
    private String selectSql;

    public DbExecutorImpl(Connection connection, Class<T> clazz){
        this.connection = connection;
        this.clazz = clazz;
        getReflectionParameters();
    }

    private void getReflectionParameters(){
        fields = getObjectFields(clazz);
        keyFiled = getKeyFieldName(fields);
        createSql = createSqlCreate(clazz, fields);
        updateSql = createSqlUpdate(clazz, fields, keyFiled);
        selectSql = createSqlSelect(clazz, keyFiled);
    }

    @Override
    public void create(T objectData) throws SQLException, IllegalAccessException {
        Savepoint savePoint = connection.setSavepoint("savePointName");
        if (keyFiled.getName() != null) {
            try (PreparedStatement pst = connection.prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS)) {
                for (int i = 1; i < fields.length; i++)
                    pst.setObject(i, fields[i].get(objectData));
                pst.executeUpdate();
            } catch (SQLException ex) {
                connection.rollback(savePoint);
                System.out.println(ex.getMessage());
                throw ex;
            }
        }
    }

    @Override
    public void update(T objectData) throws SQLException, IllegalAccessException {
        Savepoint savePoint = connection.setSavepoint("savePointName");
        try (PreparedStatement stat = connection.prepareStatement(updateSql)) {
            for (int i = 1; i < fields.length; i++)
                stat.setObject(i, fields[i].get(objectData));
            stat.setLong(fields.length, (long) keyFiled.get(objectData));
            stat.executeUpdate();
        } catch (SQLException ex) {
            connection.rollback(savePoint);
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    @Override
    public <T> T load(long id, Class<T> clazz) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Savepoint savePoint = connection.setSavepoint("savePointName");
        try (PreparedStatement stat = connection.prepareStatement(selectSql)) {
            stat.setLong(1, id);
            try (ResultSet rs = stat.executeQuery()) {
                if (rs.next())
                    return setObjectFields(clazz, rs, fields);
            }
        } catch (Exception ex) {
            connection.rollback(savePoint);
            System.out.println(ex.getMessage());
            throw ex;
        }
        return null;
    }
}