package entity;

import enums.BankNote;

import java.util.Map;

public interface ATMInterface {
    void receiveBankNote(Map<BankNote, Cell> pack);

    void giveBankNote(int amount);

    void showBalance();
}
