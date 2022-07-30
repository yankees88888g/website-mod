package net.web.fabric.http.website.handlers.posts;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import net.web.fabric.http.website.login.cred.Credentials;
import org.jetbrains.annotations.NotNull;

import static net.web.fabric.http.website.login.cred.Encryption.*;

public class LoginHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        if(read(ctx.formParam("username"), ctx.formParam("password")) != null) {
            Credentials credentials = read(ctx.formParam("username"), ctx.formParam("password"));
            ctx.sessionAttribute("YVWcMlUyh8alOG8XeKsitowrfgsfged434AM0s2AVhS5", credentials);
            ctx.req.changeSessionId();
            ctx.html(handleResponse(credentials));
        } else {

        }
    }

    private String handleResponse(Credentials credentials) {
        String response;
        if (credentials.admin == true) {//admin
            response = "<!DOCTYPE html><html><head><title>login</title><meta http-equiv = \"refresh\" content = \"0.1; url = /panel\" /></head><body><p>Logged in as " + credentials.username + "<br> Admin account" + "redirecting to panel</p></body></html>";
        } else if (credentials.admin == false) {//non admin
            response = "<!DOCTYPE html><html><head><title>login</title><meta http-equiv = \"refresh\" content = \"0.1; url = /panel\" /></head><body><p>Logged in as " + credentials.username + " redirecting to panel</p></body></html>";
        } else {
            response = "Login failed try again or reset it";
        }
        return response;
    }
}
