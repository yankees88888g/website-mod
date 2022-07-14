package net.web.fabric.http.website.handlers;

import net.minecraft.text.Text;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import static net.web.fabric.WebMain.LOGGER;

public class Arrays {
    public static List<InetAddress> addresses = new ArrayList<>();
    public static List<Integer> admins = new ArrayList<>();
    public static List<String> usernames = new ArrayList<>();
    public static List<Text> playerNames = new ArrayList<>();
    public static List<String> uuids = new ArrayList<>();

    //must have  if (addresses.get(i).equals(adr)) {}
    public static String getID(InetAddress adr) {
        for (int i = 0; i <= addresses.size(); i++) {
            if (addresses.get(i).equals(adr)) {
                return usernames.get(i);
            }
        }
        return null;
    }

    public static boolean logout(InetAddress adr) {
        for (int i = 0; i <= addresses.size(); i++) {
            if (addresses.get(i).equals(adr)) {
                addresses.remove(i);
                usernames.remove(i);
                admins.remove(i);
                playerNames.remove(i);
                return true;
            }
        }
        return false;
    }

    public static int getAdmin(InetAddress adr) {
        for (int i = 0; i <= addresses.size(); i++) {
            if (addresses.get(i).equals(adr)) {
                return admins.get(i);
            }
        }
        return 2;
    }

    public static void login(InetAddress adr, String username, boolean admin, Text playerName, String uuid) {
        addresses.add(adr);
        usernames.add(username);
        if (admin == true) {
            admins.add(0);
        } else {
            admins.add(1);
        }
        playerNames.add(playerName);
        uuids.add(uuid);
    }

    public static boolean isLoggedIn(InetAddress adr) {
        for (int i = 0; i <= addresses.size(); i++) {
            if (addresses.get(i).equals(adr)) {
                return true;
            }
        }
        return false;
    }

    public static String getPlayer(InetAddress adr) {
        for (int i = 0; i <= addresses.size(); i++) {
            if (addresses.get(i).equals(adr)) {
                return String.valueOf(playerNames.get(i));
            }
        }
        return null;
    }
    public static String getUUID(InetAddress adr) {
        for (int i = 0; i <= addresses.size(); i++) {
            if (addresses.get(i).equals(adr)) {
                return uuids.get(i);
            }
        }
        return null;
    }
}
