package ru.netrax.bridge;

public class MIR implements PaymentSystem {
    @Override
    public void work() {
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println("Мир работает...");
                Thread.sleep(2500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
