package ru.netrax.atms;

import ru.netrax.entity.Cell;
import ru.netrax.entity.RubleCell;
import ru.netrax.bridge.PaymentSystem;
import ru.netrax.enums.Ruble;
import ru.netrax.memento.Memento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RubleATM extends ATM {
    private final Map<Ruble, RubleCell> cells = new HashMap();
    private byte[] state;

    public RubleATM(int amount, PaymentSystem paymentSystem) {
        super(paymentSystem);
        ArrayList<Ruble> bankNotes = new ArrayList<>();
        bankNotes.add(Ruble.FIVE_THOUSAND);
        bankNotes.add(Ruble.TWO_THOUSAND);
        bankNotes.add(Ruble.ONE_THOUSAND);
        bankNotes.add(Ruble.FIVE_HUNDRED);
        bankNotes.add(Ruble.TWO_HUNDRED);
        bankNotes.add(Ruble.ONE_HUNDRED);
        bankNotes.add(Ruble.FIFTY);
        bankNotes.add(Ruble.TEN);
        for (Ruble i : bankNotes) {
            cells.put(i, new RubleCell(amount, i));
        }
        paymentSystem.work();
    }

    @Override
    public void receiveBankNote(Map<?, Cell> pack) {
        System.out.println("Попытка внести банкноты " + pack.size() + " номиналов в рублях");
        for (Map.Entry<Ruble, RubleCell> i : cells.entrySet())
            if (pack.containsKey(i.getKey()))
                i.getValue().setAmount(i.getValue().getAmount() + pack.get(i.getKey()).getAmount());
    }


    @Override
    public void giveBankNote(int amount) {
        System.out.println("Попытка получить рубли: " + amount);
        if (amount % 10 != 0) {
            System.out.println("Запрошенную сумму нельзя выдать");
            return;
        }
        for (Map.Entry<Ruble, RubleCell> i : cells.entrySet())
            while (i.getValue().getType().getCount() <= amount && i.getValue().getType().getCount() > 0) {
                i.getValue().setAmount(i.getValue().getAmount() - 1);
                amount -= i.getValue().getType().getCount();
            }
    }

    @Override
    public int showBalance() {
        var sum = 0;
        for (Map.Entry<Ruble, RubleCell> i : cells.entrySet())
            sum += (i.getValue().getCommonQuantity());
        return sum;
    }

    @Override
    public byte[] getState() {
        return state;
    }

    @Override
    public void setState(String state) {
        this.state = state.getBytes();
    }

    @Override
    public Memento save() {
        return new Memento(state);
    }

    @Override
    public void restore(Memento memento) {
        this.state = memento.getState();
    }

    @Override
    public int getCommonBalance() {
        return showBalance();
    }

    @Override
    public String toString() {
        return new String(state);
    }
}
