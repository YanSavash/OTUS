package ru.netrax.servlets;

import ru.netrax.models.User;
import ru.netrax.services.DBServiceInterface;
import ru.netrax.utils.TemplateUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadTableServlet extends HttpServlet {
    private TemplateUtil templateUtil;
    private DBServiceInterface<User> dbService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> root = new HashMap<>();
        root.put("admin", request.getUserPrincipal().getName());
        List<User> users = new ArrayList<>(dbService.getAllUsers());
        root.put("users", users);
        response.getWriter().append(templateUtil.getPage("showTable.tpl", root));
    }

    public LoadTableServlet(TemplateUtil templateUtil, DBServiceInterface<User> dbService) {
        this.templateUtil = templateUtil;
        this.dbService = dbService;
    }
}