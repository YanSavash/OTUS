package ru.netrax.messageSystem.messages.client;

import ru.netrax.frontend.FrontendService;
import ru.netrax.messageSystem.Address;
import ru.netrax.messageSystem.Addressee;
import ru.netrax.messageSystem.Message;

public abstract class MessageToFrontend extends Message {
    public MessageToFrontend(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof FrontendService)
            exec((FrontendService) addressee);
    }

    public abstract void exec(FrontendService frontendService);
}
