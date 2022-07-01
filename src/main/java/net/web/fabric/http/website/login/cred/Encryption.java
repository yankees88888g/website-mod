package net.web.fabric.http.website.login.cred;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

public class Encryption {

    public static boolean read(String username, String password) {
        File file = new File("config/cred");
            StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
            decryptor.setPassword(username + password + username + password);
            Properties props = new EncryptableProperties(decryptor);
            try {
                props.load(new FileInputStream("config/cred/cred.properties"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            final Logger LOGGER = LoggerFactory.getLogger("website-mod");
            LOGGER.info("worked");
            if (Objects.equals(decryptor.decrypt(props.getProperty("datasource.username")), username) && Objects.equals(decryptor.decrypt(props.getProperty("datasource.password")), password)) {
                return true;
            }
        return false;
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
        encryptor.setPassword(username + password + username + password);
        Properties props = new EncryptableProperties(encryptor);
        props.setProperty("datasource.username", encryptor.encrypt(username));
        props.setProperty("datasource.password", encryptor.encrypt(password));
        try {
            props.store(new FileOutputStream("config/cred/cred.properties"), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}