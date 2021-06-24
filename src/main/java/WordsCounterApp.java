import controller.Controller;
import controller.ControllerImpl;
import model.WebPage;
import view.ConsoleView;
import view.View;
import java.io.*;
import java.util.logging.*;
import java.util.logging.Logger;

/**@author Yashkin O,
 * 2021, this App counts unique words on the page by URL and shows statistics in console
 */

public class WordsCounterApp {

    private static Logger logger = Logger.getLogger(WordsCounterApp.class.getName());

    public static void main(String[] args) {

        try {
            LogManager.getLogManager()
                    .readConfiguration(WordsCounterApp.class.getResourceAsStream("\\logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }

        logger.log(Level.INFO, "App_Start");

        WebPage webPage = new WebPage();
        View view = new ConsoleView();
        Controller controller = new ControllerImpl(view, webPage);

        controller.setModelName(controller.downloadFile(controller.getUrl()).getName());
        controller.setModelUniqueWords();
        controller.showWordsStatistic(controller.getModelName());

        logger.log(Level.INFO, "App_the end");

    }

}