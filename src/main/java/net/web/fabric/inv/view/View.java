package net.web.fabric.inv.view;

import com.mojang.authlib.GameProfile;
import com.mojang.serialization.Dynamic;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.dimension.DimensionType;
import net.web.fabric.WebMain;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class View {


    public static ServerPlayerEntity getRequestedPlayer(String player, String uuid) {
        ServerPlayerEntity requestedPlayer = null;
        for (int i = 0; i < WebMain.getMinecraftServer().getPlayerManager().getPlayerList().size(); i++) {
            if (Objects.equals(WebMain.getMinecraftServer().getPlayerManager().getPlayerList().get(i).getUuidAsString(), uuid)) {
                requestedPlayer = WebMain.getMinecraftServer().getPlayerManager().getPlayerList().get(i);
            }
        }
        if (requestedPlayer == null) {
            requestedPlayer = WebMain.getMinecraftServer().getPlayerManager().createPlayer(new GameProfile(UUID.fromString(uuid), player), null);
            Optional<NbtCompound> compound = WebMain.getMinecraftServer().getPlayerManager().loadPlayerData(requestedPlayer);
            if (compound != null) {
                ServerWorld world = WebMain.getMinecraftServer().getWorld(DimensionType.worldFromDimensionNbt(new Dynamic<>(NbtOps.INSTANCE, compound.get())).result().get());
                if (world != null) {
                    requestedPlayer.setServerWorld(world);
                }
            }
        }
        return requestedPlayer;
    }

    public static int inv(String user, String uuid) {
        ServerPlayerEntity requestedPlayer = WebMain.getMinecraftServer().getPlayerManager().getPlayer(user);
        if(requestedPlayer == null) {
            requestedPlayer = getRequestedPlayer(user, uuid);
        }
        if (isProtected(requestedPlayer)) {
            return 0;
        } else {
            Gui gui = new Gui(user);
            for (int i = 0; i < requestedPlayer.getInventory().size(); i++) {
                gui.addSlot(i, requestedPlayer.getInventory().getStack(i));
            }
            gui.register();
        }
        return 1;
    }

    public static int eChest(String user, String uuid) {
        ServerPlayerEntity requestedPlayer = getRequestedPlayer(user, uuid);
        EnderChestInventory requestedEchest = requestedPlayer.getEnderChestInventory();

        if (isProtected(requestedPlayer)) {
            return 0;
        } else {
            GuiEC gui = new GuiEC(user);
            for (int i = 0; i < requestedEchest.size(); i++) {
                gui.addSlot(i, requestedEchest.getStack(i));
            }
            gui.register();
        }
        return 1;
    }

    public static String getUUID(String player) {
        return WebMain.getMinecraftServer().getUserCache().findByName(player).get().getId().toString();
    }

    private static boolean isProtected(ServerPlayerEntity playerEntity) {
        return false;
    }
}
