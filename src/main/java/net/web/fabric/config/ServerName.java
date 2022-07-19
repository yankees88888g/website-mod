package net.web.fabric.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ServerName {
    public static String getServerName() {
        java.io.File f = new java.io.File("config/web.config");
        if (f.exists() && f.isFile()) {
            FileReader reader = null;
            try {
                reader = new FileReader("config/web.config");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Properties prop = new Properties();
            try {
                prop.load(reader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return prop.getProperty("ServerName");
        }
        return null; //it should never get here
    }
}
