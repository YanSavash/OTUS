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
import java.util.List;

public class LoadTable extends HttpServlet {
    private DBServiceInterface<User> dbService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        InputStream inputStream = JettyStarter.class.getClassLoader().getResourceAsStream("static/showTable.html");
        if (inputStream != null)
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            }
        List<User> list = dbService.getAllUsers();
        for (User user : list) {
            stringBuilder.append(
                    "<tr>\n" +
                            "        <td>" + user.getId() + "</td>\n" +
                            "        <td>" + user.getName() + "</td>\n" +
                            "        <td>" + user.getAge() + "</td>\n" +
                            "</tr>");
        }
        stringBuilder.append(
                "</table>\n" +
                        "</body>\n" +
                        "</html>");
        System.out.println(stringBuilder);
        response.getWriter().append(stringBuilder.toString());
    }

    public LoadTable(DBServiceInterface<User> dbService) {
        this.dbService = dbService;
    }
}
