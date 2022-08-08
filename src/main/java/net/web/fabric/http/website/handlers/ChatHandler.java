package net.web.fabric.http.website.handlers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import net.web.fabric.chat.Chat;
import net.web.fabric.chat.ChatLog;
import net.web.fabric.http.website.login.cred.Credentials;
import org.jetbrains.annotations.NotNull;

public class ChatHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Credentials cred = ctx.sessionAttribute("YVWcMlUyh8alOG8XeKsitowrfgsfged434AM0s2AVhS5");
        String response;
        String requestChatValue = ctx.queryParam("msg");
        String input = "<form id=\"form\">\n" +
                "    <input name=\"chat\" type=\"text\">\n" +
                "<input name=\"password \"type=\"password\">" +
                "    <button type=\"submit\">Submit</button>\n" +
                "</form> ";
        if (cred != null) {
            StringBuilder sm = new StringBuilder();
            for (int i = 0; i < ChatLog.getChatLogs().size(); i++) {
                String sender = ChatLog.getChatLogs().get(i).sender;
                String msg = ChatLog.getChatLogs().get(i).msg;
                sm.append(sender + ": " + msg + "<br>");
            }
            if (requestChatValue != null) {
                Chat.chat(cred.playername, cred.uuid, requestChatValue);
                new ChatLog(null, null, null, "Web-" + cred.playername, requestChatValue);
                response = "<!DOCTYPE html><html lang=\"en\"><head><title>Chat Logged in as " + cred.playername + "</title></head><link rel=\"stylesheet\" href=\"chat/chat.css\"><script src=\"chat/chat.js\"></script><body><p>Chat:<br>" + sm + "</p>" + input + "</body></html>";
            } else {
                response = "<!DOCTYPE html><html lang=\"en\"><head><title>Chat Logged in as " + cred.playername + "</title></head><link rel=\"stylesheet\" href=\"chat/chat.css\"><script src=\"chat/chat.js\"></script><body><p>Chat:<br>" + sm + "</p>" + input + "</body></html>";
            }
        }else {
            response = HtmlHelper.redirect;
        }
        ctx.html(response);
    }
}
