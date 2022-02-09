package ua.com.zinchenko.ui.cli;

import ua.com.zinchenko.service.model.FileCount;
import ua.com.zinchenko.ui.UserWriter;

import java.util.List;

public interface ConsoleWriter extends UserWriter {

    void writeMenu();

    void writeEndProgram();

    void writeResult(List<FileCount> fileCountList);

    void writeException(Exception e);

    void writeMessageAboutDirectory();
}
