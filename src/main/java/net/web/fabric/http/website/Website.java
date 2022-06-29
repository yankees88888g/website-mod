package net.web.fabric.http.website;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

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

        server.start();
    }

    private static void handleRequest(HttpExchange exchange) throws IOException {

        exchange.sendResponseHeaders(200, mainHtmlFile.length);
        OutputStream os = exchange.getResponseBody();
        os.write(mainHtmlFile);
        os.close();
    }

    private static void handleCustom(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, customHomePage.length);
        OutputStream os = exchange.getResponseBody();
        os.write(customHomePage);
        os.close();
    }

    private static void handleAbout(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, aboutHtmlFile.length);
        OutputStream os = exchange.getResponseBody();
        os.write(aboutHtmlFile);
        os.close();
    }

    private static void handleMap(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, mapHtmlFile.length);
        OutputStream os = exchange.getResponseBody();
        os.write(mapHtmlFile);
        os.close();
    }

    private static void handleLogin(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, loginHtmlFile.length);
        OutputStream os = exchange.getResponseBody();
        os.write(loginHtmlFile);
        os.close();
    }
}
