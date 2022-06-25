package net.web.fabric.http.website;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import javax.script.*;
import javax.script.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static net.web.fabric.main.mainHtmlFile;


public class website {

    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        public static void main(int port) throws IOException {
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            HttpContext context = server.createContext("/");
            context.setHandler(website::handleRequest);
            server.start();
        }

        private static void handleRequest(HttpExchange exchange) throws IOException {

            //String response = htl.htl;

            exchange.sendResponseHeaders(200, mainHtmlFile.length);
            OutputStream os = exchange.getResponseBody();
            os.write(mainHtmlFile);
            //os.write(response.getBytes());
            os.close();
    }
}//InputStream is = website.class.getResourceAsStream("about_us.html");
