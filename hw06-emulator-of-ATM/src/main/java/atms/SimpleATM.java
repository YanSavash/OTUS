package atms;

import entity.ATMInterface;
import entity.Cell;
import enums.BankNote;
import java.util.*;

public class SimpleATM implements ATMInterface {

    private final Map<BankNote, Cell> cells = new HashMap();

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
            cells.put(i, new Cell(amount, i));
        }
    }

    @Override
    public void receiveBankNote(Map<BankNote, Cell> pack) {
        System.out.println("Попытка внести банкноты " + pack.size() + " номиналов");
        for (Map.Entry<BankNote, Cell> i : cells.entrySet())
            if (pack.containsKey(i.getKey()))
                i.getValue().setAmount(i.getValue().getAmount() + pack.get(i.getKey()).getAmount());
    }

    @Override
    public void giveBankNote(int amount) {
        System.out.println("Попытка получить " + amount);
        if (amount % 10 != 0) {
            System.out.println("Запрошенную сумму нельзя выдать");
            return;
        }
        for (Map.Entry<BankNote, Cell> i : cells.entrySet())
            while (i.getValue().getType().getCount() <= amount && i.getValue().getType().getCount() > 0) {
                i.getValue().setAmount(i.getValue().getAmount() - 1);
                amount -= i.getValue().getType().getCount();
            }
    }

    @Override
    public void showBalance() {
        var sum = 0;
        for (Map.Entry<BankNote, Cell> i : cells.entrySet())
            sum += (i.getValue().getCommonQuantity());
        System.out.println(sum);
    }
}
