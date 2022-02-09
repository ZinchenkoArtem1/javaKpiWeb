package ua.com.zinchenko.service.model;

public class FileCount {

    private String filePath;
    private int count;

    public FileCount() {
    }

    public FileCount(String filePath, int count) {
        this.filePath = filePath;
        this.count = count;
    }

    public String getFilePath() {
        return filePath;
    }

    public FileCount setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public int getCount() {
        return count;
    }

    public FileCount setCount(int count) {
        this.count = count;
        return this;
    }

}
