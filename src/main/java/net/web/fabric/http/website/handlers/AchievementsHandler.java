package net.web.fabric.http.website.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.web.fabric.achievements.Achievement;
import net.web.fabric.http.website.login.cred.Credentials;

import java.io.IOException;
import java.io.OutputStream;

import static net.web.fabric.http.website.login.cred.Credentials.getCred;
import static net.web.fabric.http.website.login.cred.Credentials.verify;

public class AchievementsHandler implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {
        Credentials cred = getCred(exchange.getRemoteAddress().getAddress());
        OutputStream os = exchange.getResponseBody();
        if (verify(exchange.getRemoteAddress().getAddress()) == 1) {
            String username = cred.username;
            Achievement.getAchievement(cred.username, cred.uuid);
            String response = HtmlHelper.title1 + "Achievements: " + username + HtmlHelper.title2CSS + HtmlHelper.end;
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
