package ru.netrax.executor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

import static ru.netrax.executor.ReflectionHelperCreate.getObjectFields;
import static ru.netrax.executor.ReflectionHelperCreate.setObjectFields;

public class DbExecutorImpl<T> implements DbExecutor<T> {
    private final Connection connection;
    private String sqlCreate;
    private String sqlUpdate;
    private String sqlSelect;
    private Field[] fields;
    private String id;

    public DbExecutorImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(T objectData) throws SQLException, IllegalAccessException {
        Savepoint savePoint = this.connection.setSavepoint("savePointName");
        getObjectFields(objectData);
        id = ReflectionHelperCreate.id;
        fields = ReflectionHelperCreate.fields;
        sqlCreate = ReflectionHelperCreate.sqlCreate;
        sqlUpdate = ReflectionHelperCreate.sqlUpdate;
        sqlSelect = ReflectionHelperCreate.sqlSelect;
        if (id != null) {
            try (PreparedStatement pst = connection.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS)) {
                pst.setString(1, fields[1].get(objectData).toString());
                pst.setInt(2, (int) fields[2].get(objectData));
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
        try (PreparedStatement stat = this.connection.prepareStatement(sqlUpdate)) {
            stat.setString(1, fields[1].get(objectData).toString());
            stat.setInt(2, (int) fields[2].get(objectData));
            stat.setInt(3, (int) fields[0].get(objectData));
            stat.executeUpdate();
        } catch (SQLException ex) {
            this.connection.rollback(savePoint);
            System.out.println(ex.getMessage());
            throw ex;
        }

    }

    @Override
    public <T> T load(long id, Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {
        Savepoint savePoint = this.connection.setSavepoint("savePointName");
        T user = null;
        try (PreparedStatement stat = this.connection.prepareStatement(sqlSelect)) {
            stat.setLong(1, id);
            try (ResultSet rs = stat.executeQuery()) {
                if (rs.next())
                    user = setObjectFields(clazz, rs, fields);
            }
        } catch (SQLException ex) {
            this.connection.rollback(savePoint);
            System.out.println(ex.getMessage());
            throw ex;
        }
        return user;
    }
}