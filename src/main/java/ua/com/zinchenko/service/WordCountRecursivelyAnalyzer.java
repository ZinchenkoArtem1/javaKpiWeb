package ua.com.zinchenko.service;

import ua.com.zinchenko.service.model.FileCount;

import java.util.List;

public interface WordCountRecursivelyAnalyzer {

    List<FileCount> getCountWordForInEachFileRecursively(String directoryPath);
}
