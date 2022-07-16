package net.web.fabric.http.website.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.web.fabric.http.website.login.cred.Credentials;

import java.io.IOException;
import java.io.OutputStream;

public class LogoutHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        OutputStream os = exchange.getResponseBody();
        String response;
        if (Credentials.verify(exchange.getRemoteAddress().getAddress()) == 1) {
            Credentials cred = Credentials.getCred(exchange.getRemoteAddress().getAddress());
            cred.logout(exchange.getRemoteAddress().getAddress());
        }
        response = HtmlHelper.redirect;
        exchange.sendResponseHeaders(200, response.length());
        os.write(response.getBytes());
        os.close();
    }
}
