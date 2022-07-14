package net.web.fabric.http.website.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.minecraft.text.Text;
import net.web.fabric.http.website.login.cred.Credentials;

import java.io.IOException;
import java.io.OutputStream;

import static net.web.fabric.http.website.login.cred.Encryption.*;

public class LoginHandler implements HttpHandler {

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
        if (read(username, password) == 1) {//admin
            Text playerName = getPlayerName(username, password);
            String response = "<!DOCTYPE html><html><head><title>login</title><meta http-equiv = \"refresh\" content = \"0.1; url = /panel\" /></head><body><p>Logged in as " + username + "<br> Admin account" + "redirecting to panel</p></body></html>";
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            Credentials cred = new Credentials(exchange.getRemoteAddress().getAddress(), username, true, playerName, getUUID(username, password));
            cred.register();
            os.close();
        } else if (read(username, password) == 2) {//non admin
            Text playerName = getPlayerName(username, password);
            String response = "<!DOCTYPE html><html><head><title>login</title><meta http-equiv = \"refresh\" content = \"0.1; url = /panel\" /></head><body><p>Logged in as " + username + " redirecting to panel</p></body></html>";
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            Credentials cred = new Credentials(exchange.getRemoteAddress().getAddress(), username, false, playerName, getUUID(username, password));
            cred.register();
            os.close();
        } else {
            String response = "Login failed try again or reset it";
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
        }
    }
}
