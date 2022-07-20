package net.web.fabric.http.website.login.cred;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;

@WebServlet(name = "LoginServlet",
        urlPatterns = {"/Login"},
        loadOnStartup = 1)
public class Encryption {

    public static int read(String username, String password) {
        File file = new File("config/cred");
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword("Vuqbnlesr6PEmpkd");
        Properties props = new EncryptableProperties(decryptor);
        for (int i = 0; ; ) {
            Path path = Path.of("config/cred/cred" + i + ".properties");
            if (Files.exists(path) == false) {
                return 0;
            } else {
                try {
                    props.load(new FileInputStream("config/cred/cred" + i + ".properties"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (Objects.equals(decryptor.decrypt(props.getProperty("datasource.username")), username) && Objects.equals(decryptor.decrypt(props.getProperty("datasource.password")), password)) {
                    if (Objects.equals(props.getProperty("admin"), "true")) {
                        return 1;
                    } else {
                        return 2;
                    }
                } else {
                    i++;
                }
            }
        }
    }

    public static void write(String username, String password, boolean admin, String uuid, String playerName) {
        if (username == "null") {
            return;
        } else {
            File file = new File("config/cred");
            file.mkdir();
            if (!file.isFile()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            encryptor.setPassword("Vuqbnlesr6PEmpkd");
            Properties props = new EncryptableProperties(encryptor);
            props.setProperty("datasource.username", encryptor.encrypt(username));
            props.setProperty("datasource.password", encryptor.encrypt(password));
            props.setProperty("uuid", uuid);
            props.setProperty("admin", String.valueOf(admin));
            props.setProperty("playerName", playerName);
            for (int i = 0; ; ) {
                //checks if player already has an account
                Path path = Path.of("config/cred/cred" + i + ".properties");
                if (Files.exists(path) == true) {
                    try {
                        props.load(new FileInputStream("config/cred/cred" + i + ".properties"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (Objects.equals(props.getProperty("uuid"), uuid)) {
                        try {
                            props.replace("datasource.username", encryptor.encrypt(username));
                            props.replace("datasource.password", encryptor.encrypt(password));
                            props.replace("uuid", uuid);
                            props.replace("admin", String.valueOf(admin));
                            props.replace("playerName", String.valueOf(playerName));
                            props.store(new FileOutputStream("config/cred/cred" + i + ".properties"), null);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        return;
                    }
                } else if (Files.exists(path) == false) {
                    try {
                        props.store(new FileOutputStream("config/cred/cred" + i + ".properties"), null);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return;
                } else {
                    i++;
                }
            }
        }
    }

    public static String getPlayerName(String username, String password) {
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword("Vuqbnlesr6PEmpkd");
        Properties props = new EncryptableProperties(decryptor);
        for (int i = 0; ; ) {
            Path path = Path.of("config/cred/cred" + i + ".properties");
            if (Files.exists(path) == false) {
                return null;
            }
            try {
                props.load(new FileInputStream("config/cred/cred" + i + ".properties"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (Objects.equals(decryptor.decrypt(props.getProperty("datasource.username")), username) && Objects.equals(decryptor.decrypt(props.getProperty("datasource.password")), password)) {
                return props.getProperty("playerName");
            }
        }
    }

    public static String getUUID(String username, String password) {
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword("Vuqbnlesr6PEmpkd");
        Properties props = new EncryptableProperties(decryptor);
        for (int i = 0; ; ) {
            Path path = Path.of("config/cred/cred" + i + ".properties");
            if (Files.exists(path) == false) {
                return null;
            }
            try {
                props.load(new FileInputStream("config/cred/cred" + i + ".properties"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (Objects.equals(decryptor.decrypt(props.getProperty("datasource.username")), username) && Objects.equals(decryptor.decrypt(props.getProperty("datasource.password")), password)) {
                return props.getProperty("uuid");
            }
        }
    }
}