package ru.netrax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.netrax.db.DBServiceAddresse;
import ru.netrax.db.DBServiceAddresseeImpl;
import ru.netrax.frontend.FrontendService;
import ru.netrax.frontend.FrontendServiceImpl;
import ru.netrax.messageSystem.MessageSystemContext;
import ru.netrax.messageSystem.messages.service.MessageToLoadUserList;
import ru.netrax.messageSystem.messages.service.MessageToSaveUser;
import ru.netrax.models.User;

@Controller
public class UserController {

    private MessageSystemContext context = new MessageSystemContext();

    private final FrontendService frontendService = new FrontendServiceImpl(context);
    private final DBServiceAddresse dbServiceAddressee = new DBServiceAddresseeImpl(context);

    @GetMapping({"/list"})
    public String userList(Model model) {
        model.addAttribute("users", frontendService.getList());
        return "WEB-INF/templates/userList.html";
    }

    @GetMapping({"/","/helloWorld"})
    public String hello() {
        return "WEB-INF/templates/hello.html";
    }

    @GetMapping("/create")
    public String userCreate(Model model) {
        model.addAttribute("user", new User());
        return "WEB-INF/templates/userCreate.html";
    }

    @PostMapping("/save")
    public RedirectView userSave(@ModelAttribute User user) {
        context.getMessageSystem().sendMessage(new MessageToSaveUser(context.getFrontAddress(),context.getDbAddress(),user));
        context.getMessageSystem().sendMessage(new MessageToLoadUserList(context.getFrontAddress(), context.getDbAddress()));
        return new RedirectView("/create", true);
    }
}