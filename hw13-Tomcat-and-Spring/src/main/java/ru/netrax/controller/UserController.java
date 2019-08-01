package ru.netrax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.netrax.models.User;
import ru.netrax.services.DBService;
import ru.netrax.services.DBServiceInterface;

import java.util.List;

@Controller
public class UserController {

    private final DBServiceInterface repository = new DBService();

    @GetMapping({"/", "/user/list"})
    public String userList(Model model) {
        List users = repository.getAllUsers();
        model.addAttribute("users", users);
        return "WEB-INF/templates/userList.html";
    }

    @GetMapping({"/user/helloWorld"})
    public String hello() {
        return "WEB-INF/templates/hello.html";
    }

    @GetMapping("/user/create")
    public String userCreate(Model model) {
        model.addAttribute("user", new User());
        return "WEB-INF/templates/userCreate.html";
    }

    @PostMapping("/user/save")
    public RedirectView userSave(@ModelAttribute User user) {
        repository.saveUser(user);
        return new RedirectView("/user/list", true);
    }

}