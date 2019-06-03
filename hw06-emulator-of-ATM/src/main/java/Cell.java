class Cell {
    private int amount;
    private BankNote type;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BankNote getType() {
        return type;
    }

    Cell(int amount, BankNote type) {
        this.amount = amount;
        this.type = type;
    }

    public int Sum(){
        return amount*type.getCount();
    }
}
