package ua.com.zinchenko.service.impl;

import ua.com.zinchenko.service.WordCountRecursivelyAnalyzer;
import ua.com.zinchenko.service.model.FileCount;

import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class WordCountRecursivelyAnalyzerImpl implements WordCountRecursivelyAnalyzer {

    private static final String FOR_KEY_WORD = "for";

    @Override
    public List<FileCount> getCountWordForInEachFileRecursively(String directoryPath) {
        File file = new File(directoryPath);
        if(!file.exists()) {
            throw new RuntimeException("File: " +  directoryPath + " doesn't exist");
        }
        return getCountWordForInEachFileMultithreading(getAllDirectories(file)).stream()
                .map(f -> {
                            try {
                                return f.get();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<Future<List<FileCount>>> getCountWordForInEachFileMultithreading(List<String> allDirectories) {
        ExecutorService executorService = Executors.newFixedThreadPool(8);

        return allDirectories.stream()
                .map(dir -> executorService.submit(new WordCountAnalyzerCallable(dir, FOR_KEY_WORD)))
                .toList();
    }

    private List<String> getAllDirectories(File parentDirectory) {

        List<String> allDirectories = new ArrayList<>();

        if (parentDirectory.isDirectory()) {
            allDirectories.add(parentDirectory.getAbsolutePath());

            for (File temp : Optional.ofNullable(parentDirectory.listFiles())
                    .orElseThrow(() -> new RuntimeException("Error occurred while reading the directory: " +
                            parentDirectory.getAbsolutePath()))) {
                allDirectories.addAll(getAllDirectories(temp));
            }
        }

        return allDirectories;
    }
}
