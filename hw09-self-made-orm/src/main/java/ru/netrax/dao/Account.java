package ru.netrax.dao;

import ru.netrax.annotation.Key;

import java.math.BigDecimal;

public class Account {
    @Key
    private long no;
    private String type;
    private BigDecimal rest;


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

    public void setRest(BigDecimal rest) {
        this.rest = rest;
    }

    public long getNo() {
        return no;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getRest() {
        return rest;
    }

    public Account(int no, String type, BigDecimal rest) {
        this.no = no;
        this.type = type;
        this.rest = rest;
    }

    public Account(){}
}
