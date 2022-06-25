package net.web.fabric.http.website;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static net.web.fabric.http.website.inputStream.aboutHtmlFile;
import static net.web.fabric.http.website.inputStream.mainHtmlFile;


public class website {

    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public static void main(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        HttpContext context = server.createContext("/");
        context.setHandler(website::handleRequest);

        HttpContext context_about = server.createContext("/about_us");
        context_about.setHandler(website::handleAbout);

        /*HttpContext context_map = server.createContext("/about_us");
        context_map.setHandler(website::handleMap);*/
        //TODO set this configurable with dynamp and other map mods to redirect to here.

        server.start();
    }

    private static void handleRequest(HttpExchange exchange) throws IOException {

        exchange.sendResponseHeaders(200, mainHtmlFile.length);
        OutputStream os = exchange.getResponseBody();
        os.write(mainHtmlFile);
        os.close();
    }

    private static void handleAbout(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, aboutHtmlFile.length);
        OutputStream os = exchange.getResponseBody();
        os.write(aboutHtmlFile);
        os.close();
    }
}
