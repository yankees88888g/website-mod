package net.web.fabric.http.website.handlers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import net.web.fabric.http.website.login.cred.Credentials;
import org.jetbrains.annotations.NotNull;

public class LogoutHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Credentials cred = ctx.sessionAttribute("YVWcMlUyh8alOG8XeKsitowrfgsfged434AM0s2AVhS5");
        ctx.req.getSession().invalidate();
        ctx.html(HtmlHelper.redirect);

    }
}
