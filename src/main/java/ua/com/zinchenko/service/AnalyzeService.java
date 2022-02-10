package ua.com.zinchenko.service;

import ua.com.zinchenko.service.model.FileCount;

import java.util.List;

public interface AnalyzeService {

    List<FileCount> getCountWordForInEachFileRecursively(String directoryPath);
}
