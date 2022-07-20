package net.web.fabric.chat;

import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.filter.FilteredMessage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.registry.RegistryKey;

import java.util.ArrayList;
import java.util.List;

import static javax.swing.UIManager.getString;
import static net.web.fabric.WebMain.LOGGER;

public class ChatLog {

    public static List<ChatLog> chatLog = new ArrayList<>();
    public String msg;
    public String sender;
    public RegistryKey<MessageType> mt;

    public ChatLog(FilteredMessage<SignedMessage> message, ServerPlayerEntity sender, RegistryKey<MessageType> typeKey) {
        this.msg = getString(message.filtered().getContent());
        this.sender = getString(sender.getDisplayName());
        this.mt = typeKey;
        if(chatLog.size() == 150){
            chatLog.remove(0);
        }
        chatLog.add(this);
    }

    /*public static void chatHistory(ChatLog chatLog){
        if(ChatLog.chatLog.size() == 150){
            ChatLog.chatLog.remove(0);
        }
        ChatLog.chatLog.add(chatLog);
        LOGGER.info(String.valueOf(ChatLog.chatLog.get(1)));
    }*/

    public static List<ChatLog> getChatLogs(){
        return chatLog;
    }

}
