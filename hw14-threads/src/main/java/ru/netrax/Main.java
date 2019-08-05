package ru.netrax;

public class Main {
    private final Object monitor = new Object();
    private String str = "";

    public static void main(String[] args) {
        Main main = new Main();
        main.makeRerun();
    }

    private void rerun() {
        while (true) {
            inc();
            dec();
        }
    }

    private void inc() {
        try {
            for (int i = 1; i < 10; i++) {
                synchronized (monitor) {
                    if (!Thread.currentThread().getName().equals(str)) {
                        str = Thread.currentThread().getName();
                        System.out.println(Thread.currentThread().getName() + " " + i);
                    } else i--;
                    Thread.sleep(500);
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void dec() {
        try {
            for (int i = 10; i > 1; i--) {
                synchronized (monitor) {
                    if (!Thread.currentThread().getName().equals(str)) {
                        str = Thread.currentThread().getName();
                        System.out.println(Thread.currentThread().getName() + " " + i);
                    } else i++;
                    Thread.sleep(500);
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void makeRerun() {
        Thread thread1 = new Thread(this::rerun);
        Thread thread2 = new Thread(this::rerun);
        thread1.start();
        thread2.start();
    }
}