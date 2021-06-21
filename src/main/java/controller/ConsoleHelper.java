package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class ConsoleHelper {
    private static Logger logger = Logger.getLogger(ConsoleHelper.class.getName());

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        String text = null;
        try (BufferedReader bis = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                text = bis.readLine();
                if ("exit".equals(text.trim().toLowerCase())) {
                    ConsoleHelper.writeMessage("Buy!");
                    System.exit(0);
                }   else if (text != null && isUrl(text)) {
                    break;
                } else {
                    ConsoleHelper.writeMessage("Incorrect! Try again!");
                    logger.severe(text + " is incorrect data");
                }
            }
        } catch (IOException ignored) { //suppose it will never occur
            logger.severe(ignored.getMessage());
        }
        return text;
    }

    private static boolean isUrl(String url) {
        return url.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    }
}