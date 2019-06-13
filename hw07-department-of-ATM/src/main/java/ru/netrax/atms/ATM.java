package ru.netrax.atms;

import ru.netrax.composite.Iron;
import ru.netrax.entity.ATMInterface;
import ru.netrax.bridge.PaymentSystem;
import ru.netrax.memento.Memento;

public abstract class ATM implements ATMInterface, Iron{
    private final PaymentSystem paymentSystem;
    private byte[] state;

    ATM(PaymentSystem paymentSystem) {
        this.paymentSystem = paymentSystem;
    }

    public byte[] getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state.getBytes();
    }

    public Memento save() {
        return new Memento(state);
    }

    public void restore(Memento memento) {
        this.state = memento.getState();
    }

    public int getCommonBalance() {
        return showBalance();
    }

    public String toString() {
        return new String(state);
    }
}
