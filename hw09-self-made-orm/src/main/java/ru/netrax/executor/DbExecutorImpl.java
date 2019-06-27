package ru.netrax.executor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

import static ru.netrax.helpers.QueryHelper.*;
import static ru.netrax.helpers.ReflectionHelper.*;

public class DbExecutorImpl<T> implements DbExecutor<T> {
    private final Connection connection;

    public DbExecutorImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(T objectData) throws SQLException, IllegalAccessException {
        Savepoint savePoint = this.connection.setSavepoint("savePointName");
        Field[] fields = getObjectFields(objectData);
        if (getStringKey(objectData) != null) {
            try (PreparedStatement pst = connection.prepareStatement(createSqlCreate(objectData), Statement.RETURN_GENERATED_KEYS)) {
                for (int i = 1; i < fields.length; i++)
                    pst.setObject(i, fields[i].get(objectData));
                pst.executeUpdate();
            } catch (SQLException ex) {
                this.connection.rollback(savePoint);
                System.out.println(ex.getMessage());
                throw ex;
            }
        }
    }

    @Override
    public void update(T objectData) throws SQLException, IllegalAccessException {
        Savepoint savePoint = this.connection.setSavepoint("savePointName");
        Field[] fields = getObjectFields(objectData);
        try (PreparedStatement stat = this.connection.prepareStatement(createSqlUpdate(objectData))) {
            for (int i = 1; i < fields.length; i++)
                stat.setObject(i, fields[i].get(objectData));
            stat.setLong(fields.length, getIntegerKey(objectData));
            stat.executeUpdate();
        } catch (SQLException ex) {
            this.connection.rollback(savePoint);
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    @Override
    public <T> T load(long id, Class<T> clazz) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Savepoint savePoint = this.connection.setSavepoint("savePointName");
        T user = null;
        try (PreparedStatement stat = this.connection.prepareStatement(createSqlSelect(clazz))) {
            stat.setLong(1, id);
            try (ResultSet rs = stat.executeQuery()) {
                if (rs.next())
                    user = setObjectFields(clazz, rs);
            }
        } catch (Exception ex) {
            this.connection.rollback(savePoint);
            System.out.println(ex.getMessage());
            throw ex;
        }
        return user;
    }
}