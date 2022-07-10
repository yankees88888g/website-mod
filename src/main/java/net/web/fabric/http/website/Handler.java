package net.web.fabric.http.website;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.web.fabric.http.website.login.cred.Encryption;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.Properties;

public class Handler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestParamValue = null;
        if ("GET".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handleGetRequest(httpExchange);
            String[] parts = requestParamValue.split("Sy0hckO3EJzqTdDZUMO5AIVtTGtuYvdrAJyiYmXXEwD9O6LY8Gr4g451IAVDQrmt⣆⭐⦩≽");
            String username = parts[0];
            String password = parts[1];
            handleResponse(httpExchange, requestParamValue, username, password);
            return;
        } else if ("POST".equals(httpExchange)) {
            requestParamValue = handlePostRequest(httpExchange);

        }
        handleResponse(httpExchange, requestParamValue, null, null);
        return;
    }

    private String handleGetRequest(HttpExchange httpExchange) {
        return httpExchange.getRequestURI()
                .toString()
                .split("\\?")[1]
                .split("=")[1];
    }

    private String handlePostRequest(HttpExchange httpExchange) {
        return httpExchange.getRequestURI()
                .toString()
                .split("\\?")[1]
                .split("=")[1];
    }

    private void handleResponse(HttpExchange exchange, String requestParamValue, String username, String password) throws IOException {
        File file = new File("config/cred");
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword("Vuqbnlesr6PEmpkd");
        Properties props = new EncryptableProperties(decryptor);
        try {
            props.load(new FileInputStream("config/cred/cred.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        final Logger LOGGER = LoggerFactory.getLogger("website-mod");
        LOGGER.info("worked");
        String user = decryptor.decrypt(props.getProperty("datasource.username"));
        String pass = decryptor.decrypt(props.getProperty("datasource.password"));
        if (Objects.equals(user, username) && Objects.equals(pass, password)) {
            String response = "logged in as" + requestParamValue;
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
        //StringBuilder htmlBuilder = new StringBuilder();
        String response = "login failed";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
