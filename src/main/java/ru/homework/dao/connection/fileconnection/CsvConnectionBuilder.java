package ru.homework.dao.connection.fileconnection;

import ru.homework.dao.params.FileConfigEnum;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Этот класс - имплементация интерфейса FileConnectionBuilder.
 * Реализует доступ к таблице в виде CSV файла
 */
public final class CsvConnectionBuilder implements FileConnectionBuilder {

    @Override
    public Path getFile() {
        String fileName = FileConfigEnum.PATH_TO_CSV_TABLE.getPathToFile();
        return Paths.get(fileName);
    }
}
