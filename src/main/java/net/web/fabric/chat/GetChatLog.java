package net.web.fabric.chat;

import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.filter.FilteredMessage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.registry.RegistryKey;

import java.util.ArrayList;
import java.util.List;

public class GetChatLog {

    public static List<GetChatLog> chatLog = new ArrayList<>();
    public FilteredMessage<SignedMessage> msg;
    public ServerPlayerEntity sender;
    public RegistryKey<MessageType> mt;

    public GetChatLog(FilteredMessage<SignedMessage> message, ServerPlayerEntity sender, RegistryKey<MessageType> typeKey) {
        this.msg = message;
        this.sender = sender;
        this.mt = typeKey;
    }
    public static void chatHistory(GetChatLog getChatLog){
        if(chatLog.size() == 150){
            chatLog.remove(0);
        }
        chatLog.add(getChatLog);
    }

    public static List<GetChatLog> getChatLogs(){
        return chatLog;
    }

    public static String getSender(ServerPlayerEntity sender){
        return sender.getDisplayName().toString();
    }

    public static String getMsg(FilteredMessage<SignedMessage> message){
        return String.valueOf(message.filtered().getContent());
    }
}
