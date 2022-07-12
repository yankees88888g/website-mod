package net.web.fabric.http.website.handlers;

import com.sun.net.httpserver.HttpExchange;
import net.web.fabric.write.ModCount;

import java.io.IOException;
import java.io.OutputStream;

import static net.web.fabric.http.website.InputStreamClass.html;

public class LibHandlers {
    public static void handleRequest(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("html/main.html", false, null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/main.html", false, null, false, true));
        os.close();
    }

    public static void handleMainCSS(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("html/main.css", false, null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/main.css", false, null, false, true));
        os.close();
    }

    public static void handleMainScript(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("html/main.js", false, null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/main.js", false, null, false, true));
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
        exchange.sendResponseHeaders(200, html("html/login-page.html", false, null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/login-page.html", false, null, false, true));
        //os.write(user);
        os.close();
    }

    public static void handleLoginScript(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("html/login-page.js", false, null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/login-page.js", false, null, false, true));
        os.close();
    }

    public static void handleLoginCSS(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("html/login-page.css", false, null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/login-page.css", false, null, false, true));
        os.close();
    }

    public static void handleModCount(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("config/html/mod_count.html", true, ModCount.Count, true, false).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("config/html/mod_count.html", true, ModCount.Count, true, false));
        os.close();
    }
}
