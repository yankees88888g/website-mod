package net.web.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import net.web.fabric.chat.GetChatLog;
import net.web.fabric.commands.CreateAccount;
import net.web.fabric.commands.CreateAccountAdmin;
import net.web.fabric.config.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WebMain implements ModInitializer {
    private static MinecraftServer minecraftServer;

    public static boolean isLuckPerms = false;
    public static boolean isInvView = false;
    public static final Logger LOGGER = LoggerFactory.getLogger("website-mod");

    @Override
    public void onInitialize() {
        //isLuckPerms = FabricLoader.getInstance().isModLoaded("luckperms"); for luck perms soon tm
        isInvView = FabricLoader.getInstance().isModLoaded("InvView");
		/*LOGGER.info(strArrayToStr(List.name()));
		LOGGER.info(valueOf(List.count()));
		LOGGER.info(valueOf(List.name().length));
		LOGGER.info(valueOf(FabricLoader.getInstance().getAllMods()));*/
        File.main();
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            CreateAccount.register(dispatcher);
            CreateAccountAdmin.register(dispatcher);
        });
        ServerLifecycleEvents.SERVER_STARTING.register(this::onLogicalServerStarting);
        ServerMessageEvents.CHAT_MESSAGE.register((message, sender, typeKey) -> {
            GetChatLog.chatHistory(new GetChatLog(message, sender, typeKey));
        });
    }


    private String strArrayToStr(String[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
        }
        String str = sb.toString();
        return str;
    }

    private void onLogicalServerStarting(MinecraftServer server) {
        minecraftServer = server;
    }

    public static MinecraftServer getMinecraftServer() {
        return minecraftServer;
    }
}
