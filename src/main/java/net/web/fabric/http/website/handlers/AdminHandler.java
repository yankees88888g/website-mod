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
            String response = HtmlHelper.title1 + "Admin Panel" + HtmlHelper.title2CSS + "panels/admin.css\">" + HtmlHelper.CSS2Body + "<h2>Entered admin panel as " + cred.username + "</h2><br><form action=\"/admin/inv\"><input type=\"text\" name=\"player\" id=\"player-field\" class=\"player-field\" placeholder=\"Player Name\"><button type=\"submit\" id=\"submit\">Enter</button><script src=\"html/panels/admin.js\"></script></form>" + HtmlHelper.end;
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
        } else {
            String response = HtmlHelper.redirect;
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
        }
    }
}
