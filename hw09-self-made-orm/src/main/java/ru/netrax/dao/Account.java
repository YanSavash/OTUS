package ru.netrax.dao;

import ru.netrax.annotation.Key;

public class Account {
    @Key
    private final int no;
    private final String type;
    private final int rest;

    /*• no bigint(20) NOT NULL auto_increment
    • type varchar(255)
    • rest number*/

    @Override
    public String toString() {
        return "Account{" +
                "no=" + no +
                ", type='" + type + '\'' +
                ", rest=" + rest +
                '}';
    }

    public int getNo() {
        return no;
    }

    public String getType() {
        return type;
    }

    public int getRest() {
        return rest;
    }

    public Account(int no, String type, int rest) {
        this.no = no;
        this.type = type;
        this.rest = rest;
    }
}
