package main;

import atms.SimpleATM;
import entity.Cell;
import enums.BankNote;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        SimpleATM simpleATM = new SimpleATM(1000);
        simpleATM.showBalance();
        simpleATM.giveBankNote(5562);
        simpleATM.showBalance();
        simpleATM.giveBankNote(5560);
        simpleATM.showBalance();

        Map<BankNote, Cell> pack = new HashMap<>();
        pack.put(BankNote.FIFTY, new Cell(5, BankNote.FIFTY));
        pack.put(BankNote.TEN, new Cell(5, BankNote.TEN));
        pack.put(BankNote.TWO_THOUSAND, new Cell(5, BankNote.TWO_THOUSAND));
        pack.put(BankNote.ONE_HUNDRED, new Cell(5, BankNote.ONE_HUNDRED));
        pack.put(BankNote.FIVE_THOUSAND, new Cell(5, BankNote.FIVE_THOUSAND));

        simpleATM.receiveBankNote(pack);
        simpleATM.showBalance();

        //я знаю, что здесь мапа перезатирается
        pack.put(BankNote.FIFTY, new Cell(1, BankNote.FIFTY));
        pack.put(BankNote.TEN, new Cell(1, BankNote.TEN));
        pack.put(BankNote.TWO_THOUSAND, new Cell(1, BankNote.TWO_THOUSAND));
        pack.put(BankNote.ONE_HUNDRED, new Cell(1, BankNote.ONE_HUNDRED));
        pack.put(BankNote.FIVE_THOUSAND, new Cell(1, BankNote.FIVE_THOUSAND));

        simpleATM.receiveBankNote(pack);
        simpleATM.showBalance();

    }
}
