package ru.netrax.servlets;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import ru.netrax.models.User;
import ru.netrax.services.DBServiceInterface;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

public class LoadTableServlet extends HttpServlet {
    private DBServiceInterface<User> dbService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        TemplateLoader loader = new FileTemplateLoader(new File("C:\\Users\\ysavash\\IdeaProjects\\OTUS_GIT_VERSION\\hw12-jetty\\src\\main\\resources\\static\\"));
        configuration.setTemplateLoader(loader);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        Map<String, Object> root = new HashMap<>();
        root.put("admin", request.getUserPrincipal().getName());
        List<User> users = new ArrayList<>(dbService.getAllUsers());
        root.put("users", users);
        Template template = configuration.getTemplate("showTable.tpl");
        Writer out = new StringWriter();
        try {
            template.process(root, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        response.getWriter().append(out.toString());
    }

    public LoadTableServlet(DBServiceInterface<User> dbService){
        this.dbService = dbService;
    }
}


































