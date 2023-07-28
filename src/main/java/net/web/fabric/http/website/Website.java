package net.web.fabric.http.website;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import net.web.fabric.http.website.handlers.gets.*;
import net.web.fabric.http.website.handlers.posts.LoginHandler;
import net.web.fabric.write.ModCount;

import static net.web.fabric.WebMain.LOGGER;
import static net.web.fabric.http.website.InputStreamClass.html;
public class Website {

    public static void start(int port, int homepage) {
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles(staticFiles -> {
                staticFiles.hostedPath = "/";
                staticFiles.directory = "/html";
                staticFiles.location = Location.CLASSPATH;
            });
            config.addStaticFiles(staticFiles -> {
                staticFiles.hostedPath = "/";
                staticFiles.directory = "/html/index";
                staticFiles.location = Location.CLASSPATH;
            });
            config.addStaticFiles(staticFiles -> {
                staticFiles.hostedPath = "/panel";
                staticFiles.directory = "/html/inv";
                staticFiles.location = Location.CLASSPATH;
            });
            config.addStaticFiles(staticFiles -> {
                staticFiles.hostedPath = "/admin";
                staticFiles.directory = "/html/inv";
                staticFiles.location = Location.CLASSPATH;
            });
            config.addStaticFiles(staticFiles -> {
                staticFiles.hostedPath = "/";
                staticFiles.directory = "config/html";
                staticFiles.location = Location.EXTERNAL;
            });
        }).start(port);
        if (homepage != 1) {
            app.get("/", ctx -> ctx.html(new String(html("html/index/index.html", false, null, false, true))));
        } else {
            app.get("/", ctx -> ctx.html(new String(html("config/html/custom.html", false, null, false, false))));
        }
        app.get("/about_us", ctx -> ctx.html(new String(html("config/html/about_us.html", false, null, false, false))));
        app.get("/map", ctx -> ctx.html(new String(html("config/html/map.html", true, "<!DOCTYPE html><html><meta http-equiv=\"refresh\" content=\"1; URL=REPLACE-WITH-MAP-WEBSITE\" /><html>", false, false))));
        //todo fix this ^^^^
        app.get("/login", ctx -> ctx.html(new String(html("html/login/login-page.html", false, null, false, true))));
        app.get("/mod-count", ctx -> ctx.html(new String(html("config/html/mod_count.html", true, ModCount.Count, true, false))));
        app.get("/panel", new PanelHandler());
        app.get("/admin", new AdminHandler());
        app.get("/logout", new LogoutHandler());
        app.get("/achievements", new AchievementsHandler());
        app.get("/panel/achievements", new AchievementsHandler());
        //app.get("/chat", new ChatHandler());
        //app.get("/admin/chat", new ChatAdminHandler());
        //app.get("/panel/chat", new ChatHandler());
        app.get("/panel/inv", new InvHandler());
        app.get("/admin/inv", new AInvHandler());

        app.post("/Login", new LoginHandler());

        LOGGER.info("Server started on port " + port + "!");
    }

    public static void restart() {
    }

    public static int getServerPort() {
        return 0;
    }
}
