package ru.homework.dao.params;

/**
 * Enum содержащий в себе параметры для
 * получения доступа к файлу, который содержит данные для обработки
 */
public enum FileConfigEnum {
    PATH_TO_CSV_TABLE("src/main/resources/employees.csv");

    private String pathToFile;

    FileConfigEnum(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public String getPathToFile() {
        return pathToFile;
    }

}
