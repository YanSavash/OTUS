package ru.netrax.memento;

import java.util.Map;

public class Memento {
    private final byte[] state;

    public byte[] getState() {
        return state;
    }

    public Memento(byte[] state) {
        this.state = state;
    }
}
