package ru.netrax.servlets;

import ru.netrax.models.User;
import ru.netrax.services.DBServiceInterface;
import ru.netrax.utils.ServletHelper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveNewUserServlet extends HttpServlet {
    private DBServiceInterface<User> dbService;

    public SaveNewUserServlet(DBServiceInterface<User> dbService) {
        this.dbService = dbService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        response.getWriter().append(ServletHelper.readPage("static/createUser.html"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        dbService.saveUser(getUser(request));

        response.getWriter().append(ServletHelper.readPage("static/createUser.html"));
    }

    private User getUser(HttpServletRequest request) {
        return new User(request.getParameter("name"), Integer.parseInt(request.getParameter("age")));
    }
}
