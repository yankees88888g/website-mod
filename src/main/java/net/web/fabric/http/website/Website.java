package net.web.fabric.http.website;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import net.web.fabric.http.website.handlers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@SuppressWarnings("deprecation")
public class Website {
    static HttpServer server;
    private static String contextPath;
    private static HttpHandler httpHandler;

    public Website(String contextPath, HttpServlet servlet) {
        Website.contextPath = contextPath;
        httpHandler = new HttpHandlerWithServletSupport(servlet);
    }

    public static final Logger LOGGER = LoggerFactory.getLogger("website-mod");
    static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public static void main(int port, boolean homepage) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        HttpContext httpContext = server.createContext("/");
        server.createContext(contextPath).setHandler(httpHandler);
        server.setExecutor(threadPoolExecutor);

        if (homepage == false) {
            httpContext.setHandler(LibHandlers::handleRequest);
            server.createContext("/index.js").setHandler(LibHandlers::handleIndexScript);
            server.createContext("/index.css").setHandler(LibHandlers::handleIndexCSS);
        } else {
            httpContext.setHandler(LibHandlers::handleCustom);
        }
        server.createContext("/about_us").setHandler(LibHandlers::handleAbout);
        server.createContext("/map").setHandler(LibHandlers::handleMap);
        //to be able to configurable with dynmap and other map mods to redirect to here.

        server.createContext("/login").setHandler(LibHandlers::handleLogin);
        server.createContext("/login-page.js").setHandler(LibHandlers::handleLoginScript);
        server.createContext("/login-page.css").setHandler(LibHandlers::handleLoginCSS);
        server.createContext("/mod_count").setHandler(LibHandlers::handleModCount);
        server.createContext("/admin.js").setHandler(LibHandlers::handleAdminScript);
        server.createContext("/admin.css").setHandler(LibHandlers::handleAdminCSS);
        server.createContext("/panel.js").setHandler(LibHandlers::handlePanelScript);
        server.createContext("/panel.css").setHandler(LibHandlers::handlePanelCSS);
        server.createContext("/admin/admin.js").setHandler(LibHandlers::handleAdminScript);
        server.createContext("/ach.css").setHandler(LibHandlers::handleAchCSS);
        server.createContext("/panel/ach.css").setHandler(LibHandlers::handleAchCSS);
        //server.createContext("/admin/panel.css").setHandler(LibHandlers::handleAchCSS);
        //server.createContext("/panel/inv.css").setHandler(LibHandlers::handleInvCSS);
        //server.createContext("/admin/inv.css").setHandler(LibHandlers::handleInvCSS);

        //server.createContext("/panel/inv").setHandler(InvHandler::handleInv);
        //server.createContext().setHandler(InvHandler::handleAInv);

        //server.createContext("/Login", new LoginHandler());
        //server.createContext("/panel", new PanelHandler());
        //server.createContext("/admin", new AdminHandler());
        //server.createContext("/logout", new LogoutHandler());
        //server.createContext("/achievements", new AchievementsHandler());
        //server.createContext("/panel/achievements", new AchievementsHandler());
        //server.createContext("/admin/achievements", new AchievementsHandler());
        //server.createContext("/chat", new ChatHandler());
        //server.createContext("/admin/chat", new ChatAdminHandler());
        //server.createContext("/panel/chat", new ChatHandler());

        server.setExecutor(null);
        server.start();
        LOGGER.info("Server started on port " + port + "!");
    }

    public static int getServerPort() {
        return server.getAddress().getPort();
    }

    public static void restart() {
        server.stop(0);
        server.start();
    }
}