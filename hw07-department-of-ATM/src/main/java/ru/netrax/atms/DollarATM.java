package ru.netrax.atms;

import ru.netrax.entity.Cell;
import ru.netrax.entity.DollarCell;
import ru.netrax.bridge.PaymentSystem;
import ru.netrax.enums.Dollar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DollarATM extends ATM {
    private final Map<Dollar, DollarCell> cells = new HashMap();

    public DollarATM(int amount, PaymentSystem paymentSystem) {
        super(paymentSystem);
        ArrayList<Dollar> bankNotes = new ArrayList<>();
        bankNotes.add(Dollar.FIVE_THOUSAND);
        bankNotes.add(Dollar.TWO_THOUSAND);
        bankNotes.add(Dollar.ONE_THOUSAND);
        bankNotes.add(Dollar.FIVE_HUNDRED);
        bankNotes.add(Dollar.TWO_HUNDRED);
        bankNotes.add(Dollar.ONE_HUNDRED);
        bankNotes.add(Dollar.FIFTY);
        bankNotes.add(Dollar.TEN);
        for (Dollar i : bankNotes) {
            cells.put(i, new DollarCell(amount, i));
        }
        paymentSystem.work();
    }

    @Override
    public void receiveBankNote(Map<?, Cell> pack) {
        System.out.println("Попытка внести банкноты " + pack.size() + " номиналов в долларах");
        for (Map.Entry<Dollar, DollarCell> i : cells.entrySet())
            if (pack.containsKey(i.getKey()))
                i.getValue().setAmount(i.getValue().getAmount() + pack.get(i.getKey()).getAmount());
    }


    @Override
    public void giveBankNote(int amount) {
        System.out.println("Попытка получить доллары: " + amount);
        if (amount % 10 != 0) {
            System.out.println("Запрошенную сумму нельзя выдать");
            return;
        }
        for (Map.Entry<Dollar, DollarCell> i : cells.entrySet())
            while (i.getValue().getType().getCount() <= amount && i.getValue().getType().getCount() > 0) {
                i.getValue().setAmount(i.getValue().getAmount() - 1);
                amount -= i.getValue().getType().getCount();
            }
    }

    @Override
    public int showBalance() {
        var sum = 0;
        for (Map.Entry<Dollar, DollarCell> i : cells.entrySet())
            sum += (i.getValue().getCommonQuantity());
        return sum;
    }
}