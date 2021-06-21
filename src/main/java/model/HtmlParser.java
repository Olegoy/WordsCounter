package model;

import java.util.regex.Pattern;
import java.nio.file.*;
import java.util.logging.Logger;
import java.util.*;
import java.io.*;

public class HtmlParser {
    private static Logger logger = Logger.getLogger(HtmlParser.class.getName());
    private static final String DELIMITER = "[ \\,\\.\\!\\?\\;\"\\:\\[\\]\\(\\)\n\r\t]+";

    public static String[] parseHtmlToArray(Path htmlPage){
        String[] words = null;
        StringBuilder text = new StringBuilder();
        try(BufferedReader br = new BufferedReader((new FileReader(htmlPage.toFile())))){
            while(br.ready()) {
                text.append(br.readLine() + "\r");
            }
        } catch(IOException e) {
            logger.severe(e.toString());
        }

        Pattern pattern = Pattern.compile(DELIMITER);
        String noHtmlText = text.toString().replaceAll("\\<.*?\\>", "");
        words = pattern.split(noHtmlText);
        return words;
    }

    public static Map<String, Integer> countWords(Path htmlPage) {
        String[] wordArray = parseHtmlToArray(htmlPage);
        Map<String, Integer> uniqueWords = new HashMap<>();
        for(int i = 0; i < wordArray.length; i++) {
            if(uniqueWords.containsKey(wordArray[i])) {
                uniqueWords.put(wordArray[i], uniqueWords.get(wordArray[i]) + 1);
                continue;
            }
            uniqueWords.put(wordArray[i], 1);
        }
        return sortByValue(uniqueWords);
    }

    public static Map<String, Integer> sortByValue(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o1.getValue().compareTo(o2.getValue()));
            }
        });
        HashMap<String, Integer> hm = new LinkedHashMap<String, Integer>();
        for(Map.Entry<String, Integer> si: list) {
            hm.put(si.getKey(), si.getValue());
        }
        return hm;
    }

}