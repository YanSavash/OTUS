package ru.netrax.messageSystem.messages.service;

import ru.netrax.db.DBServiceAddresse;
import ru.netrax.messageSystem.Address;
import ru.netrax.messageSystem.Addressee;
import ru.netrax.messageSystem.Message;

public abstract class MessageToDBService extends Message {
    public MessageToDBService(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof DBServiceAddresse) {
            exec((DBServiceAddresse) addressee);
        }
    }

    public abstract void exec(DBServiceAddresse dbService);
}