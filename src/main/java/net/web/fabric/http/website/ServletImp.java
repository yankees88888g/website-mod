package net.web.fabric.http.website;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static net.web.fabric.http.website.Website.LOGGER;

public class ServletImp {
    public static void runServlet() {
        new Website("/e", new HttpServlet() {
            @Override
            public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {

                String chat = req.getParameter("chat");
                LOGGER.info(chat);
            }

            public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

                String chat = req.getParameter("chat");
                LOGGER.info(chat);
            }
        });
    }
}
