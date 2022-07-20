package net.web.fabric.chat;

import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.filter.FilteredMessage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.registry.RegistryKey;

import java.util.ArrayList;
import java.util.List;


public class ChatLog {

    public static List<ChatLog> chatLog = new ArrayList<>();
    public String msg;
    public String sender;
    public RegistryKey<MessageType> mt;

    public ChatLog(FilteredMessage<SignedMessage> message, ServerPlayerEntity sender, RegistryKey<MessageType> typeKey, String web, String webMsg) {
        if(web == null || webMsg == null) {
            this.msg = message.filtered().getContent().getString();
            this.sender = sender.getDisplayName().getString();
        } else {
            this.msg = webMsg;
            this.sender = web;
        }
        this.mt = typeKey;
        if(chatLog.size() == 150){
            chatLog.remove(0);
        }
        chatLog.add(this);
    }

    public static List<ChatLog> getChatLogs(){
        return chatLog;
    }

}
