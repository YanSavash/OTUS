package ru.netrax;

import ru.netrax.jetty.JettyStarter;

public class Main {
    public static void main(String[] args) throws Exception {


//        dbService.saveUser(user);
//        System.out.println(dbService.findUser(1));

        new JettyStarter().startServer();
    }
}