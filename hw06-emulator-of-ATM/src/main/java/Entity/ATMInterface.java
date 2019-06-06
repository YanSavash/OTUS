package Entity;

import java.util.ArrayList;

public interface ATMInterface {
    void receiveBankNote(ArrayList<Cell> pack);

    void giveBankNote(int amount);

    void showBalance();
}
