package ua.com.zinchenko.ui.cli.controller;

import ua.com.zinchenko.ui.MainController;
import ua.com.zinchenko.ui.cli.ConsoleReader;
import ua.com.zinchenko.ui.cli.ConsoleWriter;

public class CliMainController implements MainController {

    ConsoleReader consoleReader;
    ConsoleWriter consoleWriter;

    public CliMainController(ConsoleReader consoleReader, ConsoleWriter consoleWriter) {
        this.consoleReader = consoleReader;
        this.consoleWriter = consoleWriter;
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
                        System.out.println("directory: " + directory);
                    }
                    case 2 -> {
                        consoleWriter.writeEndProgram();
                        isWork = false;
                    }
                    default -> consoleWriter.writeWrongTaskNumberException();
                }
            } catch (NumberFormatException e) {
                consoleWriter.writeWrongTaskNumberException();
            }
        }

    }
}
