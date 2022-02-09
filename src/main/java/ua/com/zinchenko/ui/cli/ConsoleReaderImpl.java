package ua.com.zinchenko.ui.cli;

import java.util.Scanner;

public class ConsoleReaderImpl implements ConsoleReader {

    @Override
    public int getNumberOfTaskFromMenu() {
        return Integer.parseInt((new Scanner(System.in)).nextLine());
    }

    @Override
    public String getDirectory() {
        return (new Scanner(System.in)).nextLine();
    }
}
