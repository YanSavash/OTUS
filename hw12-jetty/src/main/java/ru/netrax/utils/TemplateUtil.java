package ru.netrax.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import ru.netrax.models.User;
import ru.netrax.services.DBServiceInterface;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateUtil {
    private DBServiceInterface<User> dbService;
    private Template template;

    public TemplateUtil(DBServiceInterface<User> dbService, ServletContext servletContext) throws IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setDirectoryForTemplateLoading(new File(servletContext.getRealPath("")));
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        template = configuration.getTemplate(("showTable.tpl"));
        this.dbService = dbService;
    }

    public String getPage(HttpServletRequest request) throws IOException {
        Map<String, Object> root = new HashMap<>();
        root.put("admin", request.getUserPrincipal().getName());
        List<User> users = new ArrayList<>(dbService.getAllUsers());
        root.put("users", users);
        Writer out = new StringWriter();
        try {
            template.process(root, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
