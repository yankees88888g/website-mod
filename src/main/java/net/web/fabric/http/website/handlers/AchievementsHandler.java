package net.web.fabric.http.website.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.minecraft.advancement.Advancement;
import net.web.fabric.achievements.Ach;
import net.web.fabric.achievements.Achievement;
import net.web.fabric.http.website.login.cred.Credentials;
import org.apache.commons.logging.Log;

import java.io.IOException;
import java.io.OutputStream;

import static net.web.fabric.http.website.Website.LOGGER;
import static net.web.fabric.http.website.login.cred.Credentials.getCred;
import static net.web.fabric.http.website.login.cred.Credentials.verify;

public class AchievementsHandler implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {
        LOGGER.info("t");
        Credentials cred = getCred(exchange.getRemoteAddress().getAddress());
        OutputStream os = exchange.getResponseBody();
        if (verify(exchange.getRemoteAddress().getAddress()) == 1) {
            String username = cred.username;
            Achievement.getAchievement(cred.username, cred.uuid);
            Ach ach = Ach.getAch(cred.username);
            StringBuilder c = new StringBuilder();
            for(int i = 0, achSize = ach.achs.size(); i < achSize; i++){
                Advancement advancement = ach.achs.get(i);
                String a = advancement.getId().toString();
                boolean b = ach.done.get(i);
                c.append(a+":"+b + "<br>");
            }
            LOGGER.info(String.valueOf(c));
            String response = HtmlHelper.title1 + "Achievements: " + username + "</title>" + HtmlHelper.CSS2Body + c + HtmlHelper.end;
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
