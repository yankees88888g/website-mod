package net.web.fabric.inv.view;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.serialization.Dynamic;
import dev.jaqobb.namemcapi.NameMCAPI;
import dev.jaqobb.namemcapi.profile.ProfileRepository;
import net.minecraft.command.argument.GameProfileArgumentType;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.dimension.DimensionType;
import net.web.fabric.WebMain;

import java.util.UUID;

public class View {
    private static MinecraftServer minecraftServer = WebMain.getMinecraftServer();


    private static ServerPlayerEntity getRequestedPlayer(String player, String uuid) {
        ServerPlayerEntity requestedPlayer = minecraftServer.getPlayerManager().getPlayer(player);
        if (requestedPlayer == null) {
            requestedPlayer = minecraftServer.getPlayerManager().createPlayer(new GameProfile(UUID.fromString(uuid), player), null);
            NbtCompound compound = minecraftServer.getPlayerManager().loadPlayerData(requestedPlayer);
            if (compound != null) {
                ServerWorld world = minecraftServer.getWorld(DimensionType.worldFromDimensionNbt(new Dynamic<>(NbtOps.INSTANCE, compound.get("Dimension"))).result().get());
                if (world != null) {
                    requestedPlayer.setWorld(world);
                }
            }
        }
        return requestedPlayer;
    }

    public static int inv(String user, String uuid) {
        ServerPlayerEntity requestedPlayer = minecraftServer.getPlayerManager().getPlayer(user);
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
        return minecraftServer.getPlayerManager().getPlayer(player).getUuidAsString();
    }

    private static boolean isProtected(ServerPlayerEntity playerEntity) {
        return false;
    }
}
