package Main;

import ATMs.SimpleATM;
import Entity.Cell;
import Enums.BankNote;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        SimpleATM simpleATM = new SimpleATM(1000);
        simpleATM.showBalance();
        simpleATM.giveBankNote(5562);
        simpleATM.showBalance();
        simpleATM.giveBankNote(5560);
        simpleATM.showBalance();

        ArrayList<Cell> pack = new ArrayList<>();
        pack.add(new Cell(5, BankNote.FIFTY));
        pack.add(new Cell(5, BankNote.TEN));
        pack.add(new Cell(5, BankNote.TWO_THOUSAND));
        pack.add(new Cell(5, BankNote.ONE_HUNDRED));
        pack.add(new Cell(5, BankNote.FIVE_THOUSAND));

        simpleATM.receiveBankNote(pack);
        simpleATM.showBalance();

        pack.add(new Cell(1, BankNote.FIFTY));
        pack.add(new Cell(1, BankNote.TEN));
        pack.add(new Cell(1, BankNote.TWO_THOUSAND));
        pack.add(new Cell(1, BankNote.ONE_HUNDRED));
        pack.add(new Cell(1, BankNote.FIVE_THOUSAND));

        simpleATM.receiveBankNote(pack);
        simpleATM.showBalance();
    }
}
