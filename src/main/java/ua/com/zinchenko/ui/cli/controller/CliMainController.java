package ua.com.zinchenko.ui.cli.controller;

import ua.com.zinchenko.service.AnalyzeService;
import ua.com.zinchenko.service.model.FileCount;
import ua.com.zinchenko.ui.MainController;
import ua.com.zinchenko.ui.cli.ConsoleReader;
import ua.com.zinchenko.ui.cli.ConsoleWriter;

import java.util.List;

public class CliMainController implements MainController {

    private final ConsoleReader consoleReader;
    private final ConsoleWriter consoleWriter;
    private final AnalyzeService analyzeService;

    public CliMainController(ConsoleReader consoleReader, ConsoleWriter consoleWriter, AnalyzeService analyzeService) {
        this.consoleReader = consoleReader;
        this.consoleWriter = consoleWriter;
        this.analyzeService = analyzeService;
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
                            List<FileCount> fileCounts = analyzeService.getCountWordForInEachFileRecursively(directory);
                            consoleWriter.writeResult(fileCounts);
                        } catch (RuntimeException e) {
                            consoleWriter.writeExceptionMessage(e.getMessage());
                        }
                    }
                    case 2 -> {
                        consoleWriter.writeEndProgram();
                        isWork = false;
                    }
                    default -> consoleWriter.writeExceptionMessage("You must input 1 or 2 number");
                }
            } catch (NumberFormatException e) {
                consoleWriter.writeExceptionMessage("You must input 1 or 2 number");
            }
        }

    }
}
