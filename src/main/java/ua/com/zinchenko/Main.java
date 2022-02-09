package ua.com.zinchenko;

import ua.com.zinchenko.ui.MainController;
import ua.com.zinchenko.ui.cli.*;
import ua.com.zinchenko.ui.cli.controller.CliMainController;

public class Main {
    public static void main(String[] args) {

        ConsoleReader consoleReader = new ConsoleReaderImpl();
        ConsoleWriter consoleWriter = new ConsoleWriterImpl();
        MainController mainController = new CliMainController(consoleReader, consoleWriter);

        mainController.start();
    }
}
