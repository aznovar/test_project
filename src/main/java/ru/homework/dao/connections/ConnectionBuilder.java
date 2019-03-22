package ru.homework.dao.connections;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.nio.file.NoSuchFileException;
import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionBuilder {

    /**
     * Метод для получения соединения с БД
     *
     * @return
     * @throws SQLException
     */
    Connection getConnection() throws SQLException;
}
