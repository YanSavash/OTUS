package ru.netrax.servlets;

import ru.netrax.utils.TemplateUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogInErrorServlet extends HttpServlet {
    private TemplateUtil templateUtil;

    public LogInErrorServlet(TemplateUtil templateUtil) {
        this.templateUtil = templateUtil;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().append(templateUtil.getPage("static/login-fail.html"));
    }
}
