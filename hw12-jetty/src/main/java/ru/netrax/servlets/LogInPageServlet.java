package ru.netrax.servlets;

import ru.netrax.utils.TemplateUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LogInPageServlet extends HttpServlet {
    private TemplateUtil templateUtil;

    public LogInPageServlet(TemplateUtil templateUtil) {
        this.templateUtil = templateUtil;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().append(templateUtil.getPage("login.tpl", new HashMap<>()));
    }
}