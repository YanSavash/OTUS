package ru.netrax.jetty;

import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.authentication.FormAuthenticator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.security.Constraint;
import ru.netrax.dao.UserDaoImp;
import ru.netrax.filter_logger.SimpleFilter;
import ru.netrax.models.User;
import ru.netrax.services.DBService;
import ru.netrax.services.DBServiceInterface;
import ru.netrax.servlets.*;
import ru.netrax.utils.HibernateSessionFactoryUtil;

import java.io.*;
import java.net.URL;

public class JettyStarter {
    private final static int PORT = 8080;
    private DBServiceInterface<User> dbService = new DBService<>(new UserDaoImp(HibernateSessionFactoryUtil.
            getSessionFactory("hibernate.cfg.xml", User.class)));

    public void startServer() throws Exception {
        Server server = createServer();
        server.start();
        server.join();
    }

    private Server createServer() throws IOException {
        Server server = new Server(PORT);

        ServletContextHandler context = new ServletContextHandler(server, "/",
                ServletContextHandler.SESSIONS | ServletContextHandler.SECURITY);

        context.addServlet(new ServletHolder(new WelcomePageServlet()), "/welcome page");

        context.addServlet(new ServletHolder(new LogInPageServlet()), "/login");

        context.addServlet(new ServletHolder(new LogInErrorServlet()), "/login-error");

        context.addServlet(new ServletHolder(new SaveNewUserServlet(dbService)), "/save");

        context.addServlet(new ServletHolder(new LoadTableServlet(dbService,context.getServletContext())), "/load");

        context.addFilter(new FilterHolder(new SimpleFilter()), "/*", null);

        context.setBaseResource(Resource.newResource(JettyStarter.class.getResource("/static")));
        context.addServlet(DefaultServlet.class, "/");

        Constraint constraint = new Constraint();
        constraint.setName(Constraint.__FORM_AUTH);
        constraint.setRoles(new String[]{"user", "admin"});
        constraint.setAuthenticate(true);

        ConstraintMapping constraintMapping = new ConstraintMapping();
        constraintMapping.setConstraint(constraint);
        constraintMapping.setPathSpec("/*");

        ConstraintSecurityHandler securityHandler = new ConstraintSecurityHandler();
        securityHandler.addConstraintMapping(constraintMapping);

        URL propFile = null;
        File realmFile = new File("./realm.properties");
        if (realmFile.exists()) {
            propFile = realmFile.toURI().toURL();
        }
        if (propFile == null) {
            System.out.println("local realm config not found, looking into Resources");
            propFile = JettyStarter.class.getClassLoader().getResource("realm.properties");
        }

        if (propFile == null) {
            throw new RuntimeException("Realm property file not found");
        }

        securityHandler.setLoginService(new HashLoginService("MyRealm", propFile.getPath()));

        FormAuthenticator authenticator = new FormAuthenticator("/login", "/login-error", false);
        securityHandler.setAuthenticator(authenticator);

        context.setSecurityHandler(securityHandler);
        return server;
    }
}
