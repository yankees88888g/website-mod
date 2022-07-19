package net.web.fabric.http.website.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.web.fabric.chat.Chat;
import net.web.fabric.chat.ChatLog;
import net.web.fabric.http.website.login.cred.Credentials;

import java.io.IOException;
import java.io.OutputStream;

import static net.web.fabric.http.website.login.cred.Credentials.verify;

public class ChatHandler implements HttpHandler {

    public void handle(HttpExchange httpExchange) throws IOException {
        String requestParamValue = null;
        if ("GET".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handleGetARequest(httpExchange);
            handleChat(httpExchange, requestParamValue);
        } else {
            handleChat(httpExchange, requestParamValue);
        }
    }

    private static String handleGetARequest(HttpExchange httpExchange) {
        return httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1];
    }

    public void handleChat(HttpExchange exchange, String requestChatValue) throws IOException {
        OutputStream os = exchange.getResponseBody();
        Credentials cred = Credentials.getCred(exchange.getRemoteAddress().getAddress());
        String response;
        if (verify(exchange.getRemoteAddress().getAddress()) == 1) {
            Chat.chat(String.valueOf(cred.playername), cred.uuid, requestChatValue);
            StringBuilder sm = new StringBuilder();
            for(int i = 0; ChatLog.getChatLogs().size() > i; i++) {
                String sender = ChatLog.getSender(ChatLog.getChatLogs().get(i).sender);
                String msg = ChatLog.getMsg(ChatLog.getChatLogs().get(i).msg);
                sm.append(sender + ": " + msg);
            }
            response = "<!DOCTYPE html><html lang=\"en\"><head><title>Chat Logged in as " + cred.playername + "</title></head><link rel=\"stylesheet\" href=\"CSSGoes here><body><p>" + sm + "</p></body></html>";
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
        } else {
            response = HtmlHelper.redirect;
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
        }
    }
}
