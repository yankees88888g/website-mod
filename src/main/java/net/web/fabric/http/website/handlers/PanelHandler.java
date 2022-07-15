package net.web.fabric.http.website.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.web.fabric.http.website.login.cred.Credentials;

import java.io.IOException;
import java.io.OutputStream;

import static net.web.fabric.http.website.login.cred.Credentials.getCred;

public class PanelHandler implements HttpHandler {


    public void handle(HttpExchange exchange) throws IOException {
        Credentials cred = getCred(exchange.getRemoteAddress().getAddress());
        String username = cred.username;
        if (exchange.getRemoteAddress().getAddress().equals(cred.address)) {
            OutputStream os = exchange.getResponseBody();
            String response = "Entered panel as " + username;
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
        } else if (!exchange.getRemoteAddress().getAddress().equals(cred.address)) {
            OutputStream os = exchange.getResponseBody();
            String response = "<html><head><title>login</title><meta http-equiv = \"refresh\" content = \"0.1; url = /\" /></head><body><p>error redirecting</p></body></html>";
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
        }
    }
}

