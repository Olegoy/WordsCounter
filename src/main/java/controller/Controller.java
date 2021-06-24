package controller;

import java.io.File;
import java.net.URL;
import java.util.Map;

public interface Controller {
    void showWordsStatistic(String fileName);

    URL getUrl();

    File downloadFile(URL url);

    String getWordsStatistics(String fileName);

    void setModelName(String name);

    String getModelName();

    void setModelUniqueWords();

    Map<String, Integer> getModelUniqueWords();

    void saveToDatabase();
}
