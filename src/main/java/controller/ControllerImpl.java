package controller;

import view.View;
import model.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.logging.Logger;

public class ControllerImpl implements Controller {
    private static Logger logger = Logger.getLogger(ControllerImpl.class.getName());
    View view;
    WebPage model;

    public ControllerImpl(View view, WebPage model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void showWordsStatistic(String fileName) {
        view.showWordsStatistic(getWordsStatistics(fileName));
    }

    @Override
    public URL getUrl() {
        ConsoleHelper.writeMessage("Enter URL (for example: https://www.simbirsoft.com/) or exit.");
        URL url = null;
        String urlName = null;
        while (true) {
            urlName = ConsoleHelper.readString();
            if(urlName != null) {
                try {
                    url = new URL(urlName);
                    break;
                } catch (MalformedURLException e) {
                    logger.severe(e.getMessage());
                    continue;
                }
            }
        }
        return url;
    }



    @Override
    public File downloadFile(URL url) {
        File file = null;
        try {
            file = FileDownloader.downloadHtml(url);
        } catch (UnknownHostException e) {
            ConsoleHelper.writeMessage("Server is not available or URL is incorrect. Try again, please!");
            logger.severe(e.toString());
            System.exit(0);
        }
        return file;
    }

    @Override
    public String getWordsStatistics(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Map.Entry<String,Integer> pair: getModelUniqueWords().entrySet()) {
            stringBuilder.append(pair.getKey() + " = " + pair.getValue() + "\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public void setModelName(String name) {
        model.setName(name);
    }

    @Override
    public String getModelName() {
        return model.getName();
    }

    @Override
    public void setModelUniqueWords() {
        model.setUniqueWords(HtmlParser.countWords(Paths.get(model.getName())));
    }

    @Override
    public Map<String, Integer> getModelUniqueWords() {
        return model.getUniqueWords();
    }

    @Override
    public void saveToDatabase() {
//        TODO; daoJdbc...
    }
}
