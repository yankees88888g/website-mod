package net.web.fabric.config;

import java.io.*;
import java.util.Properties;

import static net.web.fabric.config.FirstTime.fst;

public class File {
    public static int port = 8001;

    public static boolean homepage = false;
    //public static String maplink = "replace this with your ip";

    public static int[] main() {
        try {
            try {
                java.io.File f = new java.io.File("config/web.config");
                if (f.exists() && f.isFile()) {
                    FileReader reader = new FileReader("config/web.config");
                    Properties prop = new Properties();
                    prop.load(reader);
                    Integer port = Integer.parseInt(prop.getProperty("port"));
                    boolean homepage = Boolean.parseBoolean(prop.getProperty("customHomepage"));
                    int hp = 0;
                    if (homepage == true) {
                        hp = 1;
                    }
                    int[] x = new int[2];
                    x[0] = hp;
                    x[1] = port;
                    return x;
                } else {
                    fst();
                    int hp = 0;
                    if (homepage == true) {
                        hp = 1;
                    }
                    int[] x = new int[2];
                    x[0] = hp;
                    x[1] = port;
                    return x;
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
