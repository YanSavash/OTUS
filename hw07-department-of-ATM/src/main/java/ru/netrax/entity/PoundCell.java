package ru.netrax.entity;


import ru.netrax.enums.Pound;

public class PoundCell implements Cell {
    private int amount;
    private final Pound type;

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public Pound getType() {
        return type;
    }

    public PoundCell(int amount, Pound type) {
        this.amount = amount;
        this.type = type;
    }

    @Override
    public int getCommonQuantity() {
        return amount * type.getCount();
    }
}
