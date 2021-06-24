package model;

import java.util.regex.Pattern;
import java.nio.file.*;
import java.util.logging.Logger;
import java.util.*;
import java.io.*;
import java.util.stream.Stream;

public class HtmlParser {
    private static Logger logger = Logger.getLogger(HtmlParser.class.getName());
    private static final String DELIMITER = "[ \\,\\.\\!\\?\\;\"\\:\\[\\]\\(\\)\n\r\t]+";

    public static String[] parseHtmlToArray(Path htmlPage){
        String[] words = null;
        StringBuilder text = new StringBuilder();
        try(BufferedReader br = new BufferedReader((new FileReader(htmlPage.toFile())))){
            while(br.ready()) {
                text.append(br.readLine());
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

        public static <K, V extends Comparable<? super V>> Map<K,V> sortByValue(Map<K, V> map) {
            Map<K, V> result = new LinkedHashMap<>();
            Stream<Map.Entry<K, V>> stream = map.entrySet().stream();
            stream.sorted(Map.Entry.comparingByValue()).
                    forEach(e -> result.put(e.getKey(), e.getValue()));
            return result;
        }

}