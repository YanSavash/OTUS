package Enums;

public enum BankNote {
    TEN(10),
    FIFTY(50),
    ONE_HUNDRED(100),
    TWO_HUNDRED(200),
    FIVE_HUNDRED(500),
    ONE_THOUSAND(1000),
    TWO_THOUSAND(2000),
    FIVE_THOUSAND(5000);

    private int count;

    BankNote(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BankNote{" +
                "count=" + count +
                '}';
    }

    public int getCount() {
        return count;
    }
}
