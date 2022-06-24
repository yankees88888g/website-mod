package net.web.fabric.http.website;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import net.web.fabric.config.file;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class website {
    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        public static void main(int port) throws IOException {
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", port), 0);
            HttpContext context = server.createContext("/");
            context.setHandler(website::handleRequest);
            server.start();
        }

        private static void handleRequest(HttpExchange exchange) throws IOException {
            String response = "Welcome from Server!";
            exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
    }
}
