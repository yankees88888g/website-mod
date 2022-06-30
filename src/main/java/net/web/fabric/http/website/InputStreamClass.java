package net.web.fabric.http.website;

import java.io.*;

public class InputStreamClass {
    public static byte[] HtmlFile;

    public static byte[] html(String sFile, boolean booWrite, String write, boolean rewrite, boolean internal) {
        new File("config/html").mkdirs();
        if (internal == true) {
            InputStream is = InputStreamClass.class.getClassLoader().getResourceAsStream(sFile);
            try {
                HtmlFile = is.readAllBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return HtmlFile;
        } else {
            File file = new File(sFile);
            if (!file.exists() || rewrite) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (booWrite == true) {
                    FileWriter myWriter = null;
                    try {
                        myWriter = new FileWriter(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        myWriter.write(write);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        myWriter.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                InputStream is;
                try {
                    is = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                try {
                    HtmlFile = is.readAllBytes();
                    return HtmlFile;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;//it should never get here
    }
}
