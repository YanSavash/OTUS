package ru.netrax;

import ru.netrax.dao.Account;
import ru.netrax.dao.User;
import ru.netrax.executor.DbExecutor;
import ru.netrax.executor.DbExecutorImpl;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Main {
    private static final String URL = "jdbc:h2:mem:";

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL);
        connection.setAutoCommit(false);
        return connection;
    }

    private void createTableUser(Connection connection) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement("create table User(id bigint(20) NOT NULL auto_increment, name varchar(255), age int(3))")) {
            pst.executeUpdate();
        }
    }

    private void createTableAccount(Connection connection) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement("create table Account(no bigint(20) NOT NULL auto_increment, type varchar(255), rest number)")) {
            pst.executeUpdate();
        }
    }

    public static void main(String[] args) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Main demo = new Main();
        Connection connection = demo.getConnection();

        demo.createTableUser(connection);
        demo.createTableAccount(connection);

        User userCreate = new User(1, "name", 3);
        Account accountCreate = new Account(1, "Account test", 50);

        DbExecutor<User> executorUser = new DbExecutorImpl<>(connection);
        executorUser.create(userCreate);
        DbExecutor<Account> executorAccount = new DbExecutorImpl<>(connection);
        executorAccount.create(accountCreate);

        connection.commit();

        System.out.println("created user:  " + userCreate);
        System.out.println("created user:  " + accountCreate);

        User userUpdate = new User(1, "work", 5);
        Account accountUpdate = new Account(1, "sure work", 30);
        executorAccount.update(accountUpdate);
        executorUser.update(userUpdate);

        connection.commit();

        System.out.println("updated user:  " + userUpdate);
        System.out.println("updated user:  " + accountUpdate);

        User userResult = executorUser.load(1, User.class);
        Account accountResult = executorAccount.load(1, Account.class);
        System.out.println("Received user: " + userResult);
        System.out.println("Received user: " + accountResult);

        connection.close();
    }
}
