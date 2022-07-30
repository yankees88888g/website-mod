package net.web.fabric.http.website.handlers.gets;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import net.web.fabric.http.website.handlers.HtmlHelper;
import net.web.fabric.http.website.login.cred.Credentials;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class PanelHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        ctx.html(handler(ctx.sessionAttribute("YVWcMlUyh8alOG8XeKsitowrfgsfged434AM0s2AVhS5")));
    }


    public String handler(Credentials credentials) throws IOException {
        String a = "";
        if (credentials != null) {
            if (credentials.admin == true) {
                a = "<br><br><li><a href=\"/admin\" class=\"button\">Enter Admin Panel.</a></li>";
            }
            String username = credentials.username;
            return HtmlHelper.title1 + "Panel: " + username + HtmlHelper.title2CSS + "panel/panel.css\">" + HtmlHelper.CSS2Body + "<h3>Entered panel as " + username + "</h3><br><br><li><a href=\"/panel/inv\" class=\"button\">View Your Inventory.</a></li><br>\n" +
                    "<li><a href=\"/achievements\" class=\"button\">Achievements</a></li>" + a + "<br><br><img class=\"skin\" src=\"https://crafatar.com/renders/body/" + credentials.uuid + "\"  alt=\"body\">" + HtmlHelper.end;

        } else {
            return HtmlHelper.redirect;

        }
    }
}

