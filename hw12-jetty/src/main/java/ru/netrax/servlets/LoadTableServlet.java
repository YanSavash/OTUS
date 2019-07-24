package ru.netrax.servlets;

import ru.netrax.models.User;
import ru.netrax.services.DBServiceInterface;
import ru.netrax.utils.TemplateHelper;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class LoadTableServlet extends HttpServlet {
    private DBServiceInterface<User> dbService;
    private ServletContext servletContext;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().append(TemplateHelper.getPage(dbService,request,servletContext));
    }

    public LoadTableServlet(DBServiceInterface<User> dbService, ServletContext servletContext){
        this.dbService = dbService;
        this.servletContext = servletContext;
    }
}


































