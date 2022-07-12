package net.web.fabric.http.website.login.cred;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

@WebServlet(name = "LoginServlet",
        urlPatterns = {"/Login"},
        loadOnStartup = 1)
public class Encryption {

    public static boolean read(String username, String password) {
        File file = new File("config/cred");
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword("Vuqbnlesr6PEmpkd");
        Properties props = new EncryptableProperties(decryptor);
        for (int i = 0; ; ) {
            Path path = Path.of("config/cred/cred" + i + ".properties");
            if (Files.exists(path) == false) {
                return false;
            } else {
                try {
                    props.load(new FileInputStream("config/cred/cred" + i + ".properties"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                final Logger LOGGER = LoggerFactory.getLogger("website-mod");
                LOGGER.info("worked");
                if (Objects.equals(decryptor.decrypt(props.getProperty("datasource.username")), username) && Objects.equals(decryptor.decrypt(props.getProperty("datasource.password")), password)) {
                    return true;
                } else {
                    i++;
                }
            }
        }

    }

    public static void write(String username, String password) {
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
        for (int i = 0; ; ) {
            Path path = Path.of("config/cred/cred" + i + ".properties");
            if (Files.exists(path) == false) {
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