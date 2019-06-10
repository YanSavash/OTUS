package ru.netrax.composite;

import ru.netrax.memento.Memento;

import java.util.Map;

public interface Iron {
    int getCommonBalance();

    byte[] getState();

    void setState(String state);

    Memento save();

    void restore(Memento memento);
}
