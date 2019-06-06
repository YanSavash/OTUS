package Entity;

import Enums.BankNote;

public class Cell {
    private int amount;
    private final BankNote type;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BankNote getType() {
        return type;
    }

    public Cell(int amount, BankNote type) {
        this.amount = amount;
        this.type = type;
    }

    public int Quantity(){
        return amount*type.getCount();
    }
}
