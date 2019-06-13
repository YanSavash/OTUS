package ru.netrax.entity;

import ru.netrax.enums.Ruble;

public class RubleCell implements Cell {
    private int amount;
    private final Ruble type;

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public Ruble getType() {
        return type;
    }

    public RubleCell(int amount, Ruble type) {
        this.amount = amount;
        this.type = type;
    }

    @Override
    public int getCommonQuantity() {
        return amount * type.getCount();
    }
}
