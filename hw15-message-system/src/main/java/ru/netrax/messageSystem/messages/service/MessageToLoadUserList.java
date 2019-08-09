package ru.netrax.messageSystem.messages.service;

import ru.netrax.db.DBServiceAddresse;
import ru.netrax.messageSystem.Address;
import ru.netrax.messageSystem.messages.client.MessageGetUserList;

public class MessageToLoadUserList extends MessageToDBService {
    public MessageToLoadUserList(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(DBServiceAddresse dbService) {
        dbService.getMS().sendMessage(new MessageGetUserList(getTo(),getFrom(), dbService.getAllUsers()));
    }
}