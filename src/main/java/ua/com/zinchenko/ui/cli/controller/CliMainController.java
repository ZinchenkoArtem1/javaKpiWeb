package ua.com.zinchenko.ui.cli.controller;

import ua.com.zinchenko.service.WordCountRecursivelyAnalyzer;
import ua.com.zinchenko.service.model.FileCount;
import ua.com.zinchenko.ui.MainController;
import ua.com.zinchenko.ui.cli.ConsoleReader;
import ua.com.zinchenko.ui.cli.ConsoleWriter;

import java.util.List;

public class CliMainController implements MainController {

    private final ConsoleReader consoleReader;
    private final ConsoleWriter consoleWriter;
    private final WordCountRecursivelyAnalyzer keyWordCountInFileAnalyzer;

    public CliMainController(ConsoleReader consoleReader, ConsoleWriter consoleWriter, WordCountRecursivelyAnalyzer keyWordCountInFileAnalyzer) {
        this.consoleReader = consoleReader;
        this.consoleWriter = consoleWriter;
        this.keyWordCountInFileAnalyzer = keyWordCountInFileAnalyzer;
    }

    public void start() {

        boolean isWork = true;

        while (isWork) {
            consoleWriter.writeMenu();

            try {
                switch (consoleReader.getNumberOfTaskFromMenu()) {
                    case 1 -> {
                        consoleWriter.writeMessageAboutDirectory();
                        String directory = consoleReader.getDirectory();
                        try {
                            List<FileCount> fileCounts = keyWordCountInFileAnalyzer.getCountWordForInEachFileRecursively(directory);
                            consoleWriter.writeResult(fileCounts);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 2 -> {
                        consoleWriter.writeEndProgram();
                        isWork = false;
                    }
                    default -> System.out.println("Bad input");
                }
            } catch (NumberFormatException e) {
                System.out.println("Bad input");
            }
        }

    }
}
