package ua.com.zinchenko.ui.cli;

public class ConsoleWriterImpl implements ConsoleWriter {

    @Override
    public void writeMenu() {
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
    public void writeResult() {

    }

    @Override
    public void writeWrongTaskNumberException() {
        System.out.println("Your input is not valid task number");
    }

    @Override
    public void writeMessageAboutDirectory() {
        System.out.print("Write directory: ");
    }
}
