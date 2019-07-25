package ru.netrax.servlets;

import ru.netrax.models.User;
import ru.netrax.services.DBServiceInterface;
import ru.netrax.utils.TemplateUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class SaveNewUserServlet extends HttpServlet {
    private DBServiceInterface<User> dbService;
    private TemplateUtil templateUtil;

    public SaveNewUserServlet(DBServiceInterface<User> dbService, TemplateUtil templateUtil) {
        this.dbService = dbService;
        this.templateUtil = templateUtil;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        response.getWriter().append(templateUtil.getPage("createUser.tpl", new HashMap<>()));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        dbService.saveUser(getUser(request));
        response.getWriter().append(templateUtil.getPage("createUser.tpl", new HashMap<>()));
    }

    private User getUser(HttpServletRequest request) {
        return new User(request.getParameter("name"), Integer.parseInt(request.getParameter("age")));
    }
}