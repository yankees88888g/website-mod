package net.web.fabric.http.website.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.web.fabric.http.website.login.cred.Credentials;

import java.io.IOException;
import java.io.OutputStream;

import static net.web.fabric.http.website.login.cred.Credentials.getCred;

public class AdminHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Credentials cred = getCred(exchange.getRemoteAddress().getAddress());
        OutputStream os = exchange.getResponseBody();
        if (Credentials.verifyAdmin(exchange.getRemoteAddress().getAddress()) == 1) {
            String response = "Entered admin panel as " + cred.username;
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
        } else {
            String response = "<!DOCTYPE html><html><head><title>login</title><meta http-equiv = \"refresh\" content = \"0.1; url = /\" /></head><body><p>error redirecting</p></body></html>";
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
        }
    }
}
