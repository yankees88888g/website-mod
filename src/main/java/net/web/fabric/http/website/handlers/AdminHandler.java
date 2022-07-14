package net.web.fabric.http.website.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class AdminHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        OutputStream os = exchange.getResponseBody();
        int admin = Arrays.getAdmin(exchange.getRemoteAddress().getAddress());
        String response;
        if (admin == 0) {
            response = "Entered admin panel as " + Arrays.getID(exchange.getRemoteAddress().getAddress());
        } else {
            response = "<!DOCTYPE html><html><head><title>login</title><meta http-equiv = \"refresh\" content = \"1777.1; url = /\" /></head><body><p>error redirecting</p></body></html>";
        }
        exchange.sendResponseHeaders(200, response.length());
        os.write(response.getBytes());
        os.close();
    }
}
