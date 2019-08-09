package ru.netrax.messageSystem.messages.client;

import ru.netrax.frontend.FrontendService;
import ru.netrax.messageSystem.Address;
import ru.netrax.models.User;

import java.util.List;

public class MessageGetUserList extends MessageToFrontend {
    List<User> list;
    public MessageGetUserList(Address from, Address to, List<User> list) {
        super(from, to);
        this.list = list;
    }

    @Override
    public void exec(FrontendService frontendService) {
        frontendService.getAllUsers(list);
    }
}