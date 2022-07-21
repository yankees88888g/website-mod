package net.web.fabric.http.website.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.web.fabric.achievements.Ach;
import net.web.fabric.achievements.AchData;
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
        String response;
        if (verify(exchange.getRemoteAddress().getAddress()) == 1) {
            Achievement.getAchievement(cred.username, cred.uuid);
            Ach ach = Ach.getAch(cred.username);
            StringBuilder c = new StringBuilder();
            c.append("<div class=\"column\" style=\"background-color:#32cd32;\"><h1>Completed</h1><br>");
            for (int i = 0, achSize = ach.data.size(); i < achSize; i++) {
                AchData d = ach.data.get(i);
                if (d.done == true) {
                    String e = String.valueOf(d.icon.getItem());
                    String f = e.replace("_", "-");
                    c.append("<img src=\"https://i.imgur.com/56LneiO.png\"><i id=\"icon\" class=\"icon-minecraft icon-minecraft-" + f + "\"></i><h2 id=\"clr\">" + d.title + "</h2><p id=\"desc\">" + d.des + "</p><br>");
                }
            }
            c.append("</div><div class=\"column\" style=\"background-color:#dc143c;\"><h1>Not Completed</h1><br>");
            for (int i = 0, achSize = ach.data.size(); i < achSize; i++) {
                AchData d = ach.data.get(i);
                if (d.done == false) {
                    String e = String.valueOf(d.icon.getItem());
                    String f = e.replace("_", "-");
                    c.append("<img src=\"https://i.imgur.com/56LneiO.png\" id=\"img\"><i id=\"iconF\" class=\"icon-minecraft icon-minecraft-" + f + "\"></i><h2 id=\"clrF\">" + d.title + "</h2><p id=\"descF\">" + d.des + "</p><br>");
                }
            }
            c.append("</div>");
            response = HtmlHelper.title1 + "Achievements: " + cred.playername + "</title><link rel=\"stylesheet\" href=\"https://www.gamergeeks.net/apps/minecraft/web-developer-tools/css-blocks-and-entities/icons-minecraft-0.5.css\"></title><link rel=\"stylesheet\" href=\"ach.css\">" + HtmlHelper.CSS2Body + c + HtmlHelper.end;
        } else {
            response = HtmlHelper.redirect;
        }
        exchange.sendResponseHeaders(200, response.length());
        os.write(response.getBytes());
        os.close();
    }
}
