package net.web.fabric.http.website;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import net.web.fabric.write.ModCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static net.web.fabric.http.website.InputStreamClass.*;

public class Website {


    public static final Logger LOGGER = LoggerFactory.getLogger("website-mod");
    static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);


    public static void main(int port, boolean homepage) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        HttpContext context = server.createContext("/");
        server.setExecutor(threadPoolExecutor);
        if (homepage == false) {
            context.setHandler(Website::handleRequest);
            HttpContext context_main_script = server.createContext("/main.js");
            context_main_script.setHandler(Website::handleMainScript);
            HttpContext context_main_css = server.createContext("/main.css");
            context_main_css.setHandler(Website::handleMainCSS);
        } else {
            context.setHandler(Website::handleCustom);
        }

        HttpContext context_about = server.createContext("/about_us");
        context_about.setHandler(Website::handleAbout);

        HttpContext context_map = server.createContext("/map");
        context_map.setHandler(Website::handleMap);
        //to be able to configurable with dynamp and other map mods to redirect to here.

        HttpContext context_login = server.createContext("/login");
        context_login.setHandler(Website::handleLogin);

        HttpContext context_login_script = server.createContext("/login-page.js");
        context_login_script.setHandler(Website::handleLoginScript);

        HttpContext context_login_css = server.createContext("/login-page.css");
        context_login_css.setHandler(Website::handleLoginCSS);

        HttpContext context_modCount = server.createContext("/mod_count");
        context_modCount.setHandler(Website::handleModCount);

        HttpContext context_panel = server.createContext("/panel");

        //server.createContext("/Login", new Handler());
        server.createContext("/Login", new Handler());

        server.start();
        LOGGER.info("Server started on port " + port + "!");
    }


    private static void handleRequest(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("html/main.html", false, null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/main.html", false, null, false, true));
        os.close();
    }

    private static void handleMainCSS(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("html/main.css", false, null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/main.css", false, null, false, true));
        os.close();
    }

    private static void handleMainScript(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("html/main.js", false, null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/main.js", false, null, false, true));
        os.close();

    }

    private static void handleCustom(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("config/html/custom.html", false, null, false, false).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("config/html/custom.html", false, null, false, false));
        os.close();
    }

    private static void handleAbout(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("config/html/about_us.html", false, null, false, false).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("config/html/about_us.html", false, null, false, false));
        os.close();
    }

    private static void handleMap(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("config/html/map.html", true, "<html><meta http-equiv=\"refresh\" content=\"1; URL=REPLACE-WITH-MAP-WEBSITE\" /><html>", false, false).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("config/html/map.html", true, "<html><meta http-equiv=\"refresh\" content=\"1; URL=REPLACE-WITH-MAP-WEBSITE\" /><html>", false, false));
        os.close();
    }

    private static void handleLogin(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("html/login-page.html", false, null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/login-page.html", false, null, false, true));
        //os.write(user);
        os.close();
    }

    private static void handleLoginScript(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("html/login-page.js", false, null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/login-page.js", false, null, false, true));
        os.close();
    }

    private static void handleLoginCSS(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("html/login-page.css", false, null, false, true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/login-page.css", false, null, false, true));
        os.close();
    }

    private static void handleModCount(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("config/html/mod_count.html", true, ModCount.Count, true, false).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("config/html/mod_count.html", true, ModCount.Count, true, false));
        os.close();
    }
}