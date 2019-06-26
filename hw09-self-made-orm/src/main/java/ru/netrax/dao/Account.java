package ru.netrax.dao;

import ru.netrax.annotation.Key;

public class Account {
    @Key
    private int no;
    private String type;
    private int rest;


    @Override
    public String toString() {
        return "Account{" +
                "no=" + no +
                ", type='" + type + '\'' +
                ", rest=" + rest +
                '}';
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRest(int rest) {
        this.rest = rest;
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

    public Account(){}
}
