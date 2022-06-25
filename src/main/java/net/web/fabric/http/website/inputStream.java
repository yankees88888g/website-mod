package net.web.fabric.http.website;

import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

public class inputStream {

    public static byte[] mainHtmlFile;
    public static byte[] aboutHtmlFile;
    public static void main() {

        InputStream is = inputStream.class.getClassLoader().getResourceAsStream("html/main.html");
        try {
            mainHtmlFile = is.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            new File("config/html").mkdirs();
            File file = FabricLoader.getInstance().getConfigDir().resolve("html").resolve("about_us.html").toFile();
            if(!file.exists()) {
                file.createNewFile();
            }
            InputStream is2 = new FileInputStream(file);
            aboutHtmlFile = is2.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
