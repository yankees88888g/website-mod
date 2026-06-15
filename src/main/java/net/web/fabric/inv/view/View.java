package net.web.fabric.inv.view;

import com.mojang.authlib.GameProfile;
import com.mojang.serialization.Dynamic;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.level.storage.LevelResource;
import net.web.fabric.WebMain;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class View {


    public static ServerPlayer getRequestedPlayer(String player, String uuid) {
        ServerPlayer requestedPlayer = null;
        for (int i = 0; i < WebMain.getMinecraftServer().getPlayerList().getPlayers().size(); i++) {
            if (Objects.equals(WebMain.getMinecraftServer().getPlayerList().getPlayers().get(i).getStringUUID(), uuid)) {
                requestedPlayer = WebMain.getMinecraftServer().getPlayerList().getPlayers().get(i);
            }
        }
        if (requestedPlayer == null) {
            try {
                CompoundTag compound = loadPlayerData(UUID.fromString(uuid));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return requestedPlayer;
    }

    public static int inv(String user, String uuid) {
        ServerPlayer requestedPlayer = WebMain.getMinecraftServer().getPlayerList().getPlayerByName(user);
        if(requestedPlayer == null) {
            requestedPlayer = getRequestedPlayer(user, uuid);
        }
        if (isProtected(requestedPlayer)) {
            return 0;
        } else {
            if (requestedPlayer == null) {
                return 0;
            }
            Gui gui = new Gui(user);
            for (int i = 0; i < requestedPlayer.getInventory().getContainerSize(); i++) {
                gui.addSlot(i, requestedPlayer.getInventory().getItem(i));
            }
            gui.register();
        }
        return 1;
    }

    public static int eChest(String user, String uuid) {
        ServerPlayer requestedPlayer = getRequestedPlayer(user, uuid);
        PlayerEnderChestContainer requestedEchest = requestedPlayer.getEnderChestInventory();

        if (isProtected(requestedPlayer)) {
            return 0;
        } else {
            if (requestedPlayer == null) {
                return 0;
            }
            GuiEC gui = new GuiEC(user);
            for (int i = 0; i < requestedEchest.getContainerSize(); i++) {
                gui.addSlot(i, requestedEchest.getItem(i));
            }
            gui.register();
        }
        return 1;
    }

    public static String getUUID(String player) {
        return WebMain.getMinecraftServer().getPlayerList().getPlayerByName(player).getUUID().toString();
    }

    private static boolean isProtected(ServerPlayer playerEntity) {
        return false;
    }

    public static CompoundTag loadPlayerData(UUID uuid) throws IOException {
        Path playerFile =
                WebMain.getMinecraftServer()
                        .getWorldPath(LevelResource.PLAYER_DATA_DIR)
                        .resolve(uuid.toString() + ".dat");

        return NbtIo.readCompressed(playerFile, NbtAccounter.unlimitedHeap());
    }
}
