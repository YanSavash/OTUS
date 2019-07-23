package ru.netrax.servlets;

import ru.netrax.jetty.JettyStarter;
import ru.netrax.models.User;
import ru.netrax.services.DBServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SaveNewUser extends HttpServlet {
    private DBServiceInterface<User> dbService;

    public SaveNewUser(DBServiceInterface<User> dbService) {
        this.dbService = dbService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        InputStream inputStream = JettyStarter.class.getClassLoader().getResourceAsStream("static/createUser.html");
        if (inputStream != null)
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            }
        System.out.println(stringBuilder);
        response.getWriter().append(stringBuilder.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        System.out.println(name);
        System.out.println(age);
        User user = new User();
        user.setName(name);
        user.setAge(age);
        dbService.saveUser(user);

        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        InputStream inputStream = JettyStarter.class.getClassLoader().getResourceAsStream("static/createUser.html");
        if (inputStream != null)
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            }
        System.out.println(stringBuilder);
        response.getWriter().append(stringBuilder.toString());
    }
}
