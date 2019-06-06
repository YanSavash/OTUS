package ATMs;

import Entity.Cell;
import Enums.BankNote;
import java.util.ArrayList;
import java.util.List;

public class SimpleATM extends AbstractATM {

    private static final List<Cell> cells = new ArrayList<>();

    public SimpleATM(int amount) {
        ArrayList<BankNote> bankNotes = new ArrayList<>();
        bankNotes.add(BankNote.FIVE_THOUSAND);
        bankNotes.add(BankNote.TWO_THOUSAND);
        bankNotes.add(BankNote.ONE_THOUSAND);
        bankNotes.add(BankNote.FIVE_HUNDRED);
        bankNotes.add(BankNote.TWO_HUNDRED);
        bankNotes.add(BankNote.ONE_HUNDRED);
        bankNotes.add(BankNote.FIFTY);
        bankNotes.add(BankNote.TEN);
        for (BankNote i : bankNotes) {
            cells.add(new Cell(amount, i));
        }
    }

    @Override
    public void receiveBankNote(ArrayList<Cell> pack) {
        System.out.println("Попытка внести банкноты " + pack.size() + " номиналов");
        for (Cell p : pack)
            for (Cell i : cells)
                if (i.getType().equals(p.getType()))
                    i.setAmount(i.getAmount() + p.getAmount());
    }

    @Override
    public void giveBankNote(int amount) {
        System.out.println("Попытка получить " + amount);
        if (amount % 10 != 0) {
            System.out.println("Запрошенную сумму нельзя выдать");
            return;
        }
        for (Cell i : cells)
            while (i.getType().getCount() <= amount) {
                i.setAmount(i.getAmount() - 1);
                amount -= i.getType().getCount();
            }
    }

    @Override
    public void showBalance() {
        var sum = 0;
        for (Cell i : cells)
            sum += (i.Quantity());
        System.out.println(sum);
    }
}
