package net.web.fabric.config;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.File;

public class FirstTime {
    public static void fst() {
        new File("config/").mkdirs();
        new File("config/html").mkdirs();
        File file = new File("config/web.config");
        Properties prop = new Properties();
        prop.setProperty("port", "80");
        prop.setProperty("customHomepage", "false");
        prop.setProperty("ServerName", "Example-Name");
        try {
            prop.store(new FileOutputStream("config/web.config"), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
