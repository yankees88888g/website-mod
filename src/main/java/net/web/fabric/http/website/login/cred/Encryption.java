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
import java.util.Objects;
import java.util.Properties;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;
@WebServlet(name = "errorHandlerServlet",
        urlPatterns = {"/errorHandler"},
        loadOnStartup = 1)
public class Encryption {


    protected void doPost (HttpServletRequest req,
                           HttpServletResponse resp) throws ServletException, IOException {

        String user = req.getParameter("login");
        String password = req.getParameter("password");
        boolean cred = read(user,password);
        String output = req.getParameter("stringParameter");
    }
    public static boolean read1(String one){
        String[] parts = one.split("⣆⭐⦩≽");
        String username = parts[0]; // 004
        String password = parts[1];
        boolean ret = read(username, password);
        return ret;
    }

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