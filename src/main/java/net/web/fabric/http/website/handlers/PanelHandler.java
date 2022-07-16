package net.web.fabric.http.website.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.web.fabric.http.website.login.cred.Credentials;

import java.io.IOException;
import java.io.OutputStream;

import static net.web.fabric.http.website.login.cred.Credentials.getCred;
import static net.web.fabric.http.website.login.cred.Credentials.verify;

public class PanelHandler implements HttpHandler {


    public void handle(HttpExchange exchange) throws IOException {
        Credentials cred = getCred(exchange.getRemoteAddress().getAddress());
        OutputStream os = exchange.getResponseBody();
        if (verify(exchange.getRemoteAddress().getAddress()) == 1) {
            String username = cred.username;
            String response = HtmlHelper.title1 + "Panel: " + username + HtmlHelper.title2CSS + "panels/panel.css\">" + HtmlHelper.CSS2Body + "<h3>Entered panel as " + username + "</h3><br><br><li><a href=\"/panel/inv\" class=\"button\">View Your Inventory.</a></li>" + HtmlHelper.end;
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

