package ru.netrax.executor;

import ru.netrax.annotation.Key;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Optional;

public class DbExecutorImpl<T> implements DbExecutor<T> {
    private final Connection connection;

    public DbExecutorImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(T objectData) throws SQLException, IllegalAccessException {
        Savepoint savePoint = this.connection.setSavepoint("savePointName");
        Field[] fields = objectData.getClass().getDeclaredFields();
        for (Field field : fields)
            field.setAccessible(true);
        if (fields[0].isAnnotationPresent(Key.class)) {
            try (PreparedStatement pst = connection.prepareStatement(
                    "insert into " + objectData.getClass().getSimpleName() +
                            "(" +
                            fields[1].getName() +
                            "," +
                            fields[2].getName() +
                            ") values (\'" +
                            fields[1].get(objectData) +
                            "\'," +
                            fields[2].get(objectData) +
                            ")", Statement.RETURN_GENERATED_KEYS)) {
                pst.executeUpdate();
            } catch (SQLException ex) {
                this.connection.rollback(savePoint);
                System.out.println(ex.getMessage());
                throw ex;
            }
        }
    }

    @Override
    public <T> T load(long id, Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {
        Savepoint savePoint = this.connection.setSavepoint("savePointName");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields)
            field.setAccessible(true);
        T user1 = null;
        if (fields[0].isAnnotationPresent(Key.class)) {
            Class[] paramTypes = {fields[0].getType(), fields[1].getType(), fields[2].getType()};

            try (PreparedStatement stat = this.connection.prepareStatement("select * from " + clazz.getSimpleName() + " where " + fields[0].getName() + " = ?")) {
                stat.setLong(1, id);
                try (ResultSet rs = stat.executeQuery()) {
                    if (rs.next()) {
                        Object[] paramValues = {rs.getInt(fields[0].getName()), rs.getString(fields[1].getName()), rs.getInt(fields[2].getName())};
                        Constructor<T> constructor = clazz.getConstructor(paramTypes);
                        Optional<T> T = Optional.of(constructor.newInstance(paramValues));
                        user1 = T.isPresent() ? T.get() : null;
                    }
                }
            } catch (SQLException ex) {
                this.connection.rollback(savePoint);
                System.out.println(ex.getMessage());
                throw ex;
            }

        }
        return user1;
    }

    @Override
    public void update(T objectData) throws SQLException, IllegalAccessException {
        Savepoint savePoint = this.connection.setSavepoint("savePointName");
        Field[] fields = objectData.getClass().getDeclaredFields();
        for (Field field : fields)
            field.setAccessible(true);
        if (fields[0].isAnnotationPresent(Key.class)) {
            try (PreparedStatement stat = this.connection.prepareStatement(
                    "update " + objectData.getClass().getSimpleName() +
                            " set " + fields[1].getName() + " = \'" +
                            fields[1].get(objectData) + "\'," +
                            fields[2].getName() + " = " +
                            fields[2].get(objectData) + " where " +
                            fields[0].getName() + " = " + fields[0].get(objectData))) {
                stat.executeUpdate();
            } catch (SQLException ex) {
                this.connection.rollback(savePoint);
                System.out.println(ex.getMessage());
                throw ex;
            }
        }
    }
}
