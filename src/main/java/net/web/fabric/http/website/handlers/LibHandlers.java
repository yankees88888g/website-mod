package net.web.fabric.http.website.handlers;

import com.sun.net.httpserver.HttpExchange;
import net.web.fabric.write.ModCount;

import java.io.IOException;
import java.io.OutputStream;

import static net.web.fabric.http.website.InputStreamClass.html;

public class LibHandlers {
    public static void handleRequest(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("html/index/index.html", false, null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/index/index.html", false, null, false, true));
        os.close();
    }

    public static void handleIndexCSS(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("html/index/index.css", false, null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/index/index.css", false, null, false, true));
        os.close();
    }

    public static void handleIndexScript(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("html/index/index.js", false, null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/index/index.js", false, null, false, true));
        os.close();

    }

    public static void handleCustom(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("config/html/custom.html", false, null, false, false).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("config/html/custom.html", false, null, false, false));
        os.close();
    }

    public static void handleAbout(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("config/html/about_us.html", false, null, false, false).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("config/html/about_us.html", false, null, false, false));
        os.close();
    }

    public static void handleMap(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("config/html/map.html", true, "<html><meta http-equiv=\"refresh\" content=\"1; URL=REPLACE-WITH-MAP-WEBSITE\" /><html>", false, false).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("config/html/map.html", true, "<html><meta http-equiv=\"refresh\" content=\"1; URL=REPLACE-WITH-MAP-WEBSITE\" /><html>", false, false));
        os.close();
    }

    public static void handleLogin(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("html/login/login-page.html", false, null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/login/login-page.html", false, null, false, true));
        //os.write(user);
        os.close();
    }

    public static void handleLoginScript(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("html/login/login-page.js", false, null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/login/login-page.js", false, null, false, true));
        os.close();
    }

    public static void handleLoginCSS(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("html/login/login-page.css", false, null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/login/login-page.css", false, null, false, true));
        os.close();
    }

    public static void handleModCount(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("config/html/mod_count.html", true, ModCount.Count, true, false).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("config/html/mod_count.html", true, ModCount.Count, true, false));
        os.close();
    }

    public static void handleAdminScript(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200,html("html/panel/admin.js", false,null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/panel/admin.js", false,null, false, true));
        os.close();
    }

    public static void handleAdminCSS(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200,html("html/panel/admin.css", false,null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/panel/admin.css", false,null, false, true));
        os.close();
    }

    public static void handlePanelScript(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200,html("html/panel/panel.js", false,null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/panel/panel.js", false,null, false, true));
        os.close();
    }

    public static void handlePanelCSS(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200,html("html/panel/panel.css", false,null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/panel/panel.css", false,null, false, true));
        os.close();
    }

    public static void handleInvCSS(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200,html("html/inv/inv.css", false,null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/inv/inv.css", false,null, false, true));
        os.close();
    }
}
