package ru.netrax.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.netrax.messageSystem.MessageSystemContext;
import ru.netrax.messageSystem.Address;
import ru.netrax.messageSystem.MessageSystem;
import ru.netrax.messageSystem.messages.service.MessageToLoadUserList;
import ru.netrax.messageSystem.messages.service.MessageToSaveUser;
import ru.netrax.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FrontendServiceImpl<T> implements FrontendService<T> {
    @Autowired
    private final MessageSystemContext context;

    private final Map<Integer, String> users = new HashMap<>();
    private List<User> list;

    public FrontendServiceImpl(MessageSystemContext context) {
        this.context = context;
        init();
    }

    @Override
    public void init() {
        context.getMessageSystem().addAddressee(this);
    }

    @Override
    public void saveUser(T t) {
        context.getMessageSystem().sendMessage(new MessageToSaveUser(context.getFrontAddress(),context.getDbAddress(),(User)t));
    }

    @Override
    public void getAllUsers(List<User> list) {
        this.list = list;
    }

    @Override
    public List<User> getList() {
        context.getMessageSystem().sendMessage(new MessageToLoadUserList(context.getFrontAddress(),context.getDbAddress()));
        return list;
    }

    @Override
    public Address getAddress() {
        return context.getFrontAddress();
    }

    @Override
    public MessageSystem getMS() {
        return context.getMessageSystem();
    }
}