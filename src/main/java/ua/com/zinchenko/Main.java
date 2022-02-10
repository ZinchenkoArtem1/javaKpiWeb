package ua.com.zinchenko;

import ua.com.zinchenko.service.AnalyzeService;
import ua.com.zinchenko.service.impl.AnalyzeServiceImpl;
import ua.com.zinchenko.ui.MainController;
import ua.com.zinchenko.ui.cli.*;
import ua.com.zinchenko.ui.cli.controller.CliMainController;

public class Main {
    public static void main(String[] args) {

        ConsoleReader consoleReader = new ConsoleReaderImpl();
        ConsoleWriter consoleWriter = new ConsoleWriterImpl();
        AnalyzeService analyzeService = new AnalyzeServiceImpl();
        MainController mainController = new CliMainController(consoleReader, consoleWriter, analyzeService);

        mainController.start();
    }
}
