package ru.netrax;

import ru.netrax.jetty.JettyStarter;

public class Main {
    public static void main(String[] args) throws Exception {
        new JettyStarter().startServer();
    }
}