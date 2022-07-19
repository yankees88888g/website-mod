package net.web.fabric.config;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FirstTime {
    public static void fst() {
        Properties prop = new Properties();
        prop.setProperty("port", "8001");
        prop.setProperty("custom-homepage", "false");
        prop.setProperty("ServerName", "Example-Name");
        try {
            prop.store(new FileOutputStream("config/web.config"), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
