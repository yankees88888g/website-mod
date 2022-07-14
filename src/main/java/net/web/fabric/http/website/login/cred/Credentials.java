package net.web.fabric.http.website.login.cred;

import net.minecraft.text.Text;

import java.net.InetAddress;
import java.util.LinkedHashMap;

public class Credentials {
    public static LinkedHashMap<String, Credentials> cred = new LinkedHashMap<>();
    public String strAdr;
    public InetAddress address;
    public String username;
    public boolean admin;
    public Text playername;
    public String uuid;


    public Credentials(InetAddress adr, String username, boolean admin, Text playerName, String uuid) {
        this.strAdr = String.valueOf(adr);
        this.address = adr;
        this.username = username;
        this.admin = admin;
        this.playername = playerName;
        this.uuid = uuid;
    }
    public void register(){
        cred.put(this.strAdr, this);
    }

    public void logout(InetAddress adr) {
        cred.remove(String.valueOf(adr));
    }

    public static boolean getAdmin(InetAddress adr) {
        return cred.get(String.valueOf(adr)).admin;
    }

    public static Credentials getCred(InetAddress adr){
        return cred.get(String.valueOf(adr));
    }
}
