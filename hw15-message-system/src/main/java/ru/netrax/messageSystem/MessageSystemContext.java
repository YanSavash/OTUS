package ru.netrax.messageSystem;

import org.springframework.stereotype.Component;

@Component
public class MessageSystemContext {
    private final MessageSystem messageSystem = new MessageSystem();
    private final Address frontAddress = new Address("Frontend");
    private final Address dbAddress = new Address("DB");

    public MessageSystem getMessageSystem() {
        return messageSystem;
    }

    public Address getFrontAddress() {
        return frontAddress;
    }

    public Address getDbAddress() {
        return dbAddress;
    }
}