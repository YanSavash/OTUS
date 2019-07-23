package ru.netrax.servlets;

import ru.netrax.jetty.JettyStarter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LogInError  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        InputStream inputStream = JettyStarter.class.getClassLoader().getResourceAsStream("static/login-fail.html");
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
