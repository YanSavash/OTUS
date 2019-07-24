package ru.netrax.servlets;

import ru.netrax.utils.TemplateUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class LoadTableServlet extends HttpServlet {
    private TemplateUtil templateUtil;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().append(templateUtil.getPage(request));
    }

    public LoadTableServlet(TemplateUtil templateUtil) {
        this.templateUtil = templateUtil;
    }
}


































