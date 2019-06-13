package ru.netrax.bridge;

public class VISA implements PaymentSystem {
    @Override
    public void work() {
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println("VISA working...");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
