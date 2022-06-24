package net.web.fabric.config;

import net.web.fabric.http.website.website;

import java.io.*;
import java.net.URL;
import java.util.Properties;

import static net.web.fabric.config.firstTime.fst;

public class file {
    public static int port = 8001;
    public static void main() {
        try {

            try {
                File f = new File("web.config");
                if(f.exists() && f.isFile()) {

                    FileReader reader=new FileReader("web.config");

                    Properties prop=new Properties();
                    prop.load(reader);
                    Integer port = Integer.parseInt(prop.getProperty("port"));
                    String name = prop.getProperty("name");
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
