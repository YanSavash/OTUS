package ru.netrax.composite;

import ru.netrax.atms.ATM;
import ru.netrax.memento.Caretaker;

import java.util.HashMap;
import java.util.Map;

public class Department{
    private final Map<Integer, ATM> irons = new HashMap<>();
    public final Map<Integer, Caretaker> caretakers = new HashMap<>();
    private int i = 0;

    public void addATM(ATM iron){
        irons.put(i,iron);
        iron.setState("Available");
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(iron.save());
        caretakers.put(i,caretaker);
        i++;
    }

    public int getCommonBalance() {
        var sum = 0;
        for (Map.Entry<Integer,ATM> i : irons.entrySet()) {
            sum += i.getValue().getCommonBalance();
        }
        return sum;
    }

    public void getRestore(){
        for (Map.Entry<Integer,ATM> i : irons.entrySet()) {
            i.getValue().restore(caretakers.get(i.getKey()).getMemento());
        }
    }
}
