package ru.netrax.atms;

import ru.netrax.composite.Iron;
import ru.netrax.entity.ATMInterface;
import ru.netrax.bridge.PaymentSystem;

public abstract class ATM implements ATMInterface, Iron{
    private final PaymentSystem paymentSystem;

    ATM(PaymentSystem paymentSystem) {
        this.paymentSystem = paymentSystem;
    }
}
