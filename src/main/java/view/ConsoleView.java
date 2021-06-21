package view;

import controller.ConsoleHelper;

public class ConsoleView implements View {

    @Override
    public void showWordsStatistic(String message) {
        ConsoleHelper.writeMessage(message);
    }

}
