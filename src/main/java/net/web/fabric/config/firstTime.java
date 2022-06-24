package net.web.fabric.config;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class firstTime {
    public static void fst() {
        Properties prop = new Properties();
        prop.setProperty("name", "map");
        prop.setProperty("port", "8001");
        try {
            prop.store(new FileOutputStream("web.config"), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
