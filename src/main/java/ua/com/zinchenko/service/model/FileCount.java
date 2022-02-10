package ua.com.zinchenko.service.model;

public record FileCount(String filePath, int count) {

    public String getFilePath() {
        return filePath;
    }

    public int getCount() {
        return count;
    }
}
