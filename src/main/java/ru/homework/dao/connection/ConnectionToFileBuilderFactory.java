package ru.homework.dao.connection;

import ru.homework.dao.connection.fileconnection.CsvConnectionBuilder;
import ru.homework.dao.connection.fileconnection.FileConnectionBuilder;

public final class ConnectionToFileBuilderFactory {

    private ConnectionToFileBuilderFactory() {

    }

    public static FileConnectionBuilder getCsvFile() {
        return new CsvConnectionBuilder();
    }
}
