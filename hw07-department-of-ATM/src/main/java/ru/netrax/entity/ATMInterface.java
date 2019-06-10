package ru.netrax.entity;

import java.util.Map;

public interface ATMInterface {

    void receiveBankNote(Map<?, Cell> pack);

    void giveBankNote(int amount);

    int showBalance();
}
