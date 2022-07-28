package net.web.fabric.http.website.login.cred;

public class Credentials {
    public String username;
    public boolean admin;
    public String playername;
    public String uuid;


    public Credentials(String username, boolean admin, String playerName, String uuid) {
        this.username = username;
        this.admin = admin;
        this.playername = playerName;
        this.uuid = uuid;
    }
}
