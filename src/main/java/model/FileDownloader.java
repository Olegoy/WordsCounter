package model;

import java.io.*;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class FileDownloader {
    private static Logger logger = Logger.getLogger(FileDownloader.class.getName());

    public static File downloadHtml(URL url) throws UnknownHostException {
        String fileName = null;
        File file = null;
        if(url != null) {
            fileName = url.toExternalForm().substring(url.toExternalForm().indexOf("://") + 3, url.toExternalForm().lastIndexOf("/")) + ".html";
            if(fileName.contains("/")) {
                fileName = fileName.replaceAll("/", "_");
            }
            file = new File(fileName);
            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
                String line = "";
                while((line = bufferedReader.readLine()) != null) {
                    bufferedWriter.write(line);
                }
            } catch (IOException e2) {
                logger.severe( e2.toString() + "  The URL is not valid.");
                throw new UnknownHostException();
            }
        }
        return file;
    }

}
