package controller;

import view.View;
import model.*;

import java.io.File;
import java.net.UnknownHostException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.logging.Logger;

public class Controller {
    private static Logger logger = Logger.getLogger(Controller.class.getName());
    View view;
    WebPage model;

    public Controller(View view, WebPage model) {
        this.view = view;
        this.model = model;
    }

    public void showWordsStatistic(String fileName) {
        view.showWordsStatistic(getWordsStatistics(fileName));
    }

    public String inputUrl() {
        ConsoleHelper.writeMessage("Enter URL (for example: https://www.simbirsoft.com/) or exit.");
        File file = null;
        try {
            file = FileDownloader.downloadHtml(ConsoleHelper.readString());
        } catch (UnknownHostException e) {
            ConsoleHelper.writeMessage("Server is not available or URL is incorrect. Try again, please!");
            logger.severe(e.toString());
            System.exit(0);
        }
        return file.getName();
    }

    public String getWordsStatistics(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Map.Entry<String,Integer> pair: getModelUniqueWords().entrySet()) {
            stringBuilder.append(pair.getKey() + " = " + pair.getValue() + "\n");
        }
        return stringBuilder.toString();
    }

    public void setModelName(String name) {
        model.setName(name);
    }

    public String getModelName() {
        return model.getName();
    }

    public void setModelUniqueWords() {
        model.setUniqueWords(HtmlParser.countWords(Paths.get(model.getName())));
    }

    public Map<String, Integer> getModelUniqueWords() {
        return model.getUniqueWords();
    }

    public void saveToDatabase() {
//        TODO; daoJdbc...
    }
}
