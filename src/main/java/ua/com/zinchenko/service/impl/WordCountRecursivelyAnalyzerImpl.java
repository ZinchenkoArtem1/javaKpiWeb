package ua.com.zinchenko.service.impl;

import ua.com.zinchenko.service.WordCountRecursivelyAnalyzer;
import ua.com.zinchenko.service.model.FileCount;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class WordCountRecursivelyAnalyzerImpl implements WordCountRecursivelyAnalyzer {

    private static final String FOR_KEY_WORD = "for";

    @Override
    public List<FileCount> getCountWordForInEachFileRecursively(String directoryPath) {
        File file = new File(directoryPath);

        List<String> allDirectories = getAllDirectories(file);

        ExecutorService executorService = Executors.newFixedThreadPool(8);

        List<Future<List<FileCount>>> listFuture = allDirectories.stream()
                .map(dir -> executorService.submit(new WordCountAnalyzerCallable(dir, FOR_KEY_WORD)))
                .toList();

        return listFuture.stream()
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
