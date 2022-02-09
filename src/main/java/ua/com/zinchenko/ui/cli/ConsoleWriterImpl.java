package ua.com.zinchenko.ui.cli;

import ua.com.zinchenko.service.model.FileCount;

import java.util.List;

public class ConsoleWriterImpl implements ConsoleWriter {

    @Override
    public void writeMenu() {
        System.out.println();
        System.out.println("---MENU---");
        System.out.println("1. Input directory for compete task");
        System.out.println("2. Close program");
        System.out.print("For choose input number of task (from 1 to 2 second inclusive): ");
    }

    @Override
    public void writeEndProgram() {
        System.out.println("---End of program---");
    }

    @Override
    public void writeResult(List<FileCount> fileCountList) {
        fileCountList.forEach(fc -> System.out.println("-> file: " + fc.getFilePath() + " : " + fc.getCount()));
    }

    @Override
    public void writeException(String message) {
        System.out.println(message);
    }

    @Override
    public void writeMessageAboutDirectory() {
        System.out.print("Write directory: ");
    }
}
