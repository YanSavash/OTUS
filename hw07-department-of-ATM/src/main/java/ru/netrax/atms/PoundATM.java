package ru.netrax.atms;

import ru.netrax.entity.Cell;
import ru.netrax.entity.PoundCell;
import ru.netrax.bridge.PaymentSystem;
import ru.netrax.enums.Pound;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PoundATM extends ATM {
    private final Map<Pound, PoundCell> cells = new HashMap();

    public PoundATM(int amount, PaymentSystem paymentSystem) {
        super(paymentSystem);
        ArrayList<Pound> bankNotes = new ArrayList<>();
        bankNotes.add(Pound.FIVE_THOUSAND);
        bankNotes.add(Pound.TWO_THOUSAND);
        bankNotes.add(Pound.ONE_THOUSAND);
        bankNotes.add(Pound.FIVE_HUNDRED);
        bankNotes.add(Pound.TWO_HUNDRED);
        bankNotes.add(Pound.ONE_HUNDRED);
        bankNotes.add(Pound.FIFTY);
        bankNotes.add(Pound.TEN);
        for (Pound i : bankNotes) {
            cells.put(i, new PoundCell(amount, i));
        }
        paymentSystem.work();
    }

    @Override
    public void receiveBankNote(Map<?, Cell> pack) {
        System.out.println("Попытка внести банкноты " + pack.size() + " номиналов в фунтах");
        for (Map.Entry<Pound, PoundCell> i : cells.entrySet())
            if (pack.containsKey(i.getKey()))
                i.getValue().setAmount(i.getValue().getAmount() + pack.get(i.getKey()).getAmount());
    }


    @Override
    public void giveBankNote(int amount) {
        System.out.println("Попытка получить фунты: " + amount);
        if (amount % 10 != 0) {
            System.out.println("Запрошенную сумму нельзя выдать");
            return;
        }
        for (Map.Entry<Pound, PoundCell> i : cells.entrySet())
            while (i.getValue().getType().getCount() <= amount && i.getValue().getType().getCount() > 0) {
                i.getValue().setAmount(i.getValue().getAmount() - 1);
                amount -= i.getValue().getType().getCount();
            }
    }

    @Override
    public int showBalance() {
        var sum = 0;
        for (Map.Entry<Pound, PoundCell> i : cells.entrySet())
            sum += (i.getValue().getCommonQuantity());
        return sum;
    }
}