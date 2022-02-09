package ua.com.zinchenko;

import ua.com.zinchenko.service.WordCountRecursivelyAnalyzer;
import ua.com.zinchenko.service.impl.WordCountRecursivelyAnalyzerImpl;
import ua.com.zinchenko.ui.MainController;
import ua.com.zinchenko.ui.cli.*;
import ua.com.zinchenko.ui.cli.controller.CliMainController;

public class Main {
    public static void main(String[] args) {

        ConsoleReader consoleReader = new ConsoleReaderImpl();
        ConsoleWriter consoleWriter = new ConsoleWriterImpl();
        WordCountRecursivelyAnalyzer wordCountRecursivelyAnalyzer = new WordCountRecursivelyAnalyzerImpl();
        MainController mainController = new CliMainController(consoleReader, consoleWriter, wordCountRecursivelyAnalyzer);

        mainController.start();
    }
}
