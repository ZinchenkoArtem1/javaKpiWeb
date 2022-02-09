package ua.com.zinchenko.ui.cli;

import ua.com.zinchenko.ui.UserWriter;

public interface ConsoleWriter extends UserWriter {

    void writeMenu();

    void writeEndProgram();

    void writeResult();

    void writeWrongTaskNumberException();

    void writeMessageAboutDirectory();
}
