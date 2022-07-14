package net.web.fabric.http.website;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import net.web.fabric.http.website.handlers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@SuppressWarnings("deprecation")
public class Website {

    public static final Logger LOGGER = LoggerFactory.getLogger("website-mod");
    static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public static void main(int port, boolean homepage) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        HttpContext context = server.createContext("/");
        server.setExecutor(threadPoolExecutor);
        if (homepage == false) {
            context.setHandler(LibHandlers::handleRequest);
            HttpContext context_main_script = server.createContext("/main.js");
            context_main_script.setHandler(LibHandlers::handleMainScript);
            HttpContext context_main_css = server.createContext("/main.css");
            context_main_css.setHandler(LibHandlers::handleMainCSS);
        } else {
            context.setHandler(LibHandlers::handleCustom);
        }

        HttpContext context_about = server.createContext("/about_us");
        context_about.setHandler(LibHandlers::handleAbout);

        HttpContext context_map = server.createContext("/map");
        context_map.setHandler(LibHandlers::handleMap);
        //to be able to configurable with dynmap and other map mods to redirect to here.

        HttpContext context_login = server.createContext("/login");
        context_login.setHandler(LibHandlers::handleLogin);

        HttpContext context_login_script = server.createContext("/login-page.js");
        context_login_script.setHandler(LibHandlers::handleLoginScript);

        HttpContext context_login_css = server.createContext("/login-page.css");
        context_login_css.setHandler(LibHandlers::handleLoginCSS);

        HttpContext context_modCount = server.createContext("/mod_count");
        context_modCount.setHandler(LibHandlers::handleModCount);

        HttpContext context_inv = server.createContext("/panel/inv");
        context_inv.setHandler(InvHandler::handleInv);

        HttpContext context_Ainv = server.createContext("/admin/inv");
        context_Ainv.setHandler(InvHandler::handleAInv);

        server.createContext("/Login", new LoginHandler());
        server.createContext("/panel", new PanelHandler());
        server.createContext("/admin", new AdminHandler());

        server.setExecutor(null);
        server.start();
        LOGGER.info("Server started on port " + port + "!");
    }
}