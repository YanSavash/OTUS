package ru.netrax.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.netrax.dao.UserRepository;
import ru.netrax.dao.UserRepositoryImp;
import ru.netrax.messageSystem.MessageSystemContext;
import ru.netrax.messageSystem.Address;
import ru.netrax.messageSystem.MessageSystem;
import ru.netrax.models.User;

import java.util.List;

@Component
public class DBServiceAddresseeImpl<T> implements DBServiceAddresse<T> {
    @Autowired
    private final MessageSystemContext context;
    private UserRepository usersDao = new UserRepositoryImp();

    public DBServiceAddresseeImpl(MessageSystemContext context) {
        this.context = context;
        init();
    }

    @Override
    public void init() {
        context.getMessageSystem().addAddressee(this);
    }

    @Override
    public int getUserId(String name) {
        return name.hashCode();
    }

    @Override
    public T findUser(long id) {
        return (T) usersDao.findById(id);
    }

    @Override
    public void saveUser(T user) {
        usersDao.save((User) user);
    }

    @Override
    public List<User> getAllUsers() {
        return usersDao.getAllUsers();
    }

    @Override
    public Address getAddress() {
        return context.getDbAddress();
    }

    @Override
    public MessageSystem getMS() {
        return context.getMessageSystem();
    }
}