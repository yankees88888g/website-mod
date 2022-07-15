package net.web.fabric.http.website.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.web.fabric.http.website.login.cred.Credentials;

import java.io.IOException;
import java.io.OutputStream;

public class AdminHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        OutputStream os = exchange.getResponseBody();
        Credentials cred = Credentials.getCred(exchange.getRemoteAddress().getAddress());
        boolean admin = cred.admin;
        String response;
        if (admin && exchange.getRemoteAddress().getAddress().equals(cred.address)) {
            response = "Entered admin panel as " + cred.username;
        } else {
            response = "<!DOCTYPE html><html><head><title>login</title><meta http-equiv = \"refresh\" content = \"0.1; url = /\" /></head><body><p>error redirecting</p></body></html>";
        }
        exchange.sendResponseHeaders(200, response.length());
        os.write(response.getBytes());
        os.close();
    }
}
