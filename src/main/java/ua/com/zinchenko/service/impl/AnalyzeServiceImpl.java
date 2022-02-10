package ua.com.zinchenko.service.impl;

import ua.com.zinchenko.service.AnalyzeService;
import ua.com.zinchenko.service.model.FileCount;

import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class AnalyzeServiceImpl implements AnalyzeService {

    private static final String FOR_KEY_WORD = "for";

    @Override
    public List<FileCount> getCountWordForInEachFileRecursively(String directoryPath) {
        File file = new File(directoryPath);
        if (!file.exists()) {
            throw new RuntimeException("File: " + directoryPath + " doesn't exist");
        }
        return getCountWordForInEachFileMultithreaded(getAllDirectories(file)).stream()
                .map(f -> {
                    try {
                        return f.get();
                    } catch (InterruptedException e) {
                        throw new RuntimeException("One of thread was interrupted");
                    } catch (ExecutionException e) {
                        throw new RuntimeException("We have exception in one of thread: " + e.getCause().getMessage());
                    }}
                )
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<Future<List<FileCount>>> getCountWordForInEachFileMultithreaded(List<String> allDirectories) {
        ExecutorService executorService = Executors.newFixedThreadPool(8);

        return allDirectories.stream()
                .map(dir -> executorService.submit(new DirectoryAnalyzerCallable(dir, FOR_KEY_WORD)))
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
