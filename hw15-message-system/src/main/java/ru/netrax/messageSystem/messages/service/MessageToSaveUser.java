package ru.netrax.messageSystem.messages.service;

import ru.netrax.db.DBServiceAddresse;
import ru.netrax.messageSystem.Address;
import ru.netrax.models.User;

public class MessageToSaveUser extends MessageToDBService {
    private User user;

    public MessageToSaveUser(Address from, Address to, User user) {
        super(from, to);
        this.user = user;
    }

    @Override
    public void exec(DBServiceAddresse dbService) {
        dbService.saveUser(user);
    }
}
