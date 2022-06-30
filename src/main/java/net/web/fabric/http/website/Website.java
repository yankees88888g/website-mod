package net.web.fabric.http.website;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import net.web.fabric.write.ModCount;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import static net.web.fabric.http.website.InputStreamClass.*;

public class Website {

   public static void main(int port, boolean homepage) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        HttpContext context = server.createContext("/");
        if(homepage == false) {
            context.setHandler(Website::handleRequest);
        }else{
            context.setHandler(Website::handleCustom);
        }

        HttpContext context_about = server.createContext("/about_us");
        context_about.setHandler(Website::handleAbout);

        HttpContext context_map = server.createContext("/map");
        context_map.setHandler(Website::handleMap);
        //to be able to configurable with dynamp and other map mods to redirect to here.

        HttpContext context_login = server.createContext("/login");
        context_login.setHandler(Website::handleLogin);

        HttpContext context_modCount = server.createContext("/mod_count");
        context_modCount.setHandler(Website::handleModCount);

        server.start();
    }

    private static void handleRequest(HttpExchange exchange) throws IOException {

        exchange.sendResponseHeaders(200, html("html/main.html",false,null,false,true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("html/main.html",false,null,false,true));
        os.close();
    }

    private static void handleCustom(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("config/html/custom.html",false,null,false,false).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("config/html/custom.html",false,null,false,false));
        os.close();
    }

    private static void handleAbout(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("config/html/about_us.html",false,null,false,false).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("config/html/about_us.html",false,null,false,false));
        os.close();
    }

    private static void handleMap(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("config/html/map.html",true,"<html><meta http-equiv=\"refresh\" content=\"1; URL=REPLACE-WITH-MAP-WEBSITE\" /><html>",false,false).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("config/html/map.html",true,"<html><meta http-equiv=\"refresh\" content=\"1; URL=REPLACE-WITH-MAP-WEBSITE\" /><html>",false,false));
        os.close();
    }

    private static void handleLogin(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("login/login-page.html",false,null,false,true).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("login/login-page.html",false,null,false,true));
        os.close();
    }
    private static void handleModCount(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, html("config/html/mod_count.html", true, ModCount.Count, true,false).length);
        OutputStream os = exchange.getResponseBody();
        os.write(html("config/html/mod_count.html", true, ModCount.Count,true,false));
        os.close();
    }
}
