package ru.netrax.messageSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("LoopStatementThatDoesntLoop")
public final class MessageSystem {
    private final static Logger logger = Logger.getLogger(MessageSystem.class.getName());

    private final List<Thread> workers;
    private final Map<Address, LinkedBlockingQueue<Message>> messagesMap;
    private final Map<Address, Addressee> addresseeMap;

    public MessageSystem() {
        workers = new ArrayList<>();
        messagesMap = new HashMap<>();
        addresseeMap = new HashMap<>();
    }

    public void addAddressee(Addressee addressee) {
        addresseeMap.put(addressee.getAddress(), addressee);
        messagesMap.put(addressee.getAddress(), new LinkedBlockingQueue<>());
        start();
    }

    public void sendMessage(Message message) {
        Address from = message.getFrom();
        Address to = message.getTo();
        logger.info(String.format("Send message %s from %s to %s", message, from.getId(), to.getId()));
        messagesMap.get(to).add(message);
    }


    public void start() {
        for (Map.Entry<Address, Addressee> entry : addresseeMap.entrySet()) {
            String name = "MS-worker-" + entry.getKey().getId();
            Thread thread = new Thread(() -> {
                while (true) {
                    LinkedBlockingQueue<Message> queue = messagesMap.get(entry.getKey());
                    while (true) {
                        try {
                            Message message = queue.take(); //Blocks
                            message.exec(entry.getValue());
                        } catch (InterruptedException e) {
                            logger.log(Level.INFO, "Thread interrupted. Finishing: " + name);
                            return;
                        }
                    }
                }
            });
            thread.setName(name);
            thread.start();
            workers.add(thread);
        }
    }

    public void dispose() {
        workers.forEach(Thread::interrupt);
    }
}
