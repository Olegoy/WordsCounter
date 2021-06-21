package model;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class FileDownloader {
    private static Logger logger = Logger.getLogger(FileDownloader.class.getName());

    public static File downloadHtml(String webPage) throws UnknownHostException {
        URL url = null;
        String fileName = webPage.substring(webPage.indexOf("www."), webPage.lastIndexOf("/")) + ".html";
        File file = new File(fileName);
        try{
            url = new URL(webPage);
        } catch (MalformedURLException e1) {
            logger.severe("The URL is not valid." + e1.toString());
        }

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
        return file;
    }

}
