package ru.netrax.entity;

import ru.netrax.enums.Dollar;

public class DollarCell implements Cell {
    private int amount;
    private final Dollar type;

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public Dollar getType() {
        return type;
    }

    public DollarCell(int amount, Dollar type) {
        this.amount = amount;
        this.type = type;
    }

    @Override
    public int getCommonQuantity() {
        return amount * type.getCount();
    }
}
