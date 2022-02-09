package ua.com.zinchenko.service.impl;

import ua.com.zinchenko.service.model.FileCount;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

public record WordCountAnalyzerCallable(String directoryPath,
                                        String word) implements Callable<List<FileCount>> {

    @Override
    public List<FileCount> call() {
        System.out.println(Thread.currentThread().getName());
        File directory = new File(directoryPath);

        return Arrays.stream(
                        Optional.ofNullable(directory.listFiles())
                                .orElseThrow(
                                        () -> new RuntimeException("Error occurred while reading the directory: " +
                                                directory.getAbsolutePath())
                                )
                )
                .filter(File::isFile)
                .filter(f -> f.getName().contains(".c"))
                .map(file -> new FileCount(
                                file.getAbsolutePath(),
                                getForKeyWordCount(file)
                        )
                ).toList();
    }

    private int getForKeyWordCount(File file) {
        String line;
        int count = 0;

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File with name: " + file.getAbsolutePath() + " doesn't found");
        }

        try {
            while ((line = br.readLine()) != null) {
                count += Arrays.stream(line.split(" "))
                        .filter(w -> w.equals(word))
                        .count();
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return count;
    }
}
