package ru.homework.dao.params;

public enum FileConfigEnum {
    PATH_TO_CSV_TABLE("src/main/resources/customers.csv");

    private String pathToFile;

    FileConfigEnum(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public String getPathToFile() {
        return pathToFile;
    }

}
