package net.web.fabric.config;

//import net.web.fabric.http.website.htl;
import net.web.fabric.http.website.website;

import java.io.*;
import java.util.Properties;

import static net.web.fabric.config.firstTime.fst;

public class file {
    public static int port = 8001;

    public static boolean map =  false;
    public static String maplink = "replace this with your ip";

    public static void main() {
        try {

            try {
                File f = new File("config/web.config");
                if(f.exists() && f.isFile()) {

                    FileReader reader=new FileReader("config/web.config");

                    Properties prop=new Properties();
                    prop.load(reader);
                    Integer port = Integer.parseInt(prop.getProperty("port"));
                    boolean map = Boolean.parseBoolean(prop.getProperty("map"));
                    String maplink = prop.getProperty("maplink");
                    try {
                        website.main(port);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    fst();
                    try {
                        website.main(port);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
