package net.web.fabric.http.website.login.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static net.web.fabric.http.website.Website.LOGGER;
import static net.web.fabric.http.website.login.cred.Encryption.read;

@WebServlet(name = "LoginServlet", urlPatterns = {"/loginServlet/"})
public class LoginServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
    }

    public LoginServlet(){
        super();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        read(username,password);
        LOGGER.info(username);
        response.setContentType("<html>" + username + password + "</html>");
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("test");
    }
}
