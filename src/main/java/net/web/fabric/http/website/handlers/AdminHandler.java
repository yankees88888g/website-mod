package net.web.fabric.http.website.handlers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import net.web.fabric.http.website.login.cred.Credentials;
import org.jetbrains.annotations.NotNull;

public class AdminHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Credentials cred = ctx.sessionAttribute("YVWcMlUyh8alOG8XeKsitowrfgsfged434AM0s2AVhS5");
        String response;
        if (cred != null) {
            response = HtmlHelper.title1 + "Admin Panel" + HtmlHelper.title2CSS + "panel/admin.css\">" + HtmlHelper.CSS2Body + "<h2>Entered admin panel as " + cred.username + "</h2><br><input type=\"text\" name=\"player\" id=\"player\" class=\"player-field\" placeholder=\"Player Name\"><button type=\"submit\" id=\"submit\">Enter</button><script src=\"panel/admin.js\"></script>" + HtmlHelper.end;
        } else {
            response = HtmlHelper.redirect;
        }
        ctx.html(response);
    }
}
