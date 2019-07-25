package ru.netrax.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.Map;

public class TemplateUtil {
    private Configuration configuration;

    public TemplateUtil(ServletContext servletContext) throws IOException {
        configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setDirectoryForTemplateLoading(new File(servletContext.getRealPath("")));
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
    }

    public String getPage(String htmlPage, Map<String, Object> root) throws IOException {
        Template template = configuration.getTemplate((htmlPage));
        Writer out = new StringWriter();
        try {
            template.process(root, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
