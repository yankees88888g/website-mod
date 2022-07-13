package net.web.fabric.http.website.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.io.OutputStream;

import static net.web.fabric.http.website.login.cred.Encryption.read;

public class PanelHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestParamValue = null;
        if ("GET".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handleGetRequest(httpExchange);
            handleResponse(httpExchange, requestParamValue);
        } else if ("POST".equals(httpExchange)) {
            requestParamValue = handlePostRequest(httpExchange);
        }
        handleResponse(httpExchange, requestParamValue);

    }

    private String handleGetRequest(HttpExchange httpExchange) {
        return httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1];
    }

    private String handlePostRequest(HttpExchange httpExchange) {
        return httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1];
    }

    private void handleResponse(HttpExchange exchange, String requestParamValue) throws IOException {
        String[] parts = requestParamValue.split("Sy0hckO3EJzqTdDZUMO5AIVtTGtuYvdrAJyiYmXXEwD9O6LY8Gr4g451IAVDQrmt");
        String username = parts[0];
        String password = parts[1];
        OutputStream os = exchange.getResponseBody();

        if (read(username, password) == 1) {
            String response = "<html>Logged in as " + username + "<br>Admin account" + "<li><a href=\"panel\">Panel</a></li></html>";
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
        } else if (read(username, password) == 2) {
            String response = "<html>Logged in as " + username + "<li><a href=\"panel\">Panel</a></li></html>";
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
        } else {
            String response = "Login failed try again or reset it";
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
        }
    }
}

