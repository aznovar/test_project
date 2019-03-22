package ru.homework.dao.connections;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import ru.homework.dao.config.GlobalConfigDatabase;

import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Класс реализующий интерфейс ConnectionBuilder
 * Позволяет, на основе параметров, подключиться к базе данных
 * При подключении используется интерфейс DataSource
 */
public class MySQLConnectionBuilder implements ConnectionBuilder {

    private ComboPooledDataSource dataSource;

    /**
     * Конструктор класса MySQLConnectionBuilder.
     * Устанавливает соответствующие параметры для соединения,
     * которые берутся из проперти файла database.properties
     */
    public MySQLConnectionBuilder() {
        try {
            dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(GlobalConfigDatabase.getProperty("db.driver.class"));
            dataSource.setJdbcUrl(GlobalConfigDatabase.getProperty("db.url"));
            dataSource.setUser(GlobalConfigDatabase.getProperty("db.login"));
            dataSource.setPassword(GlobalConfigDatabase.getProperty("db.password"));
            dataSource.setMaxPoolSize(20);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    @Override
    /**
     * Метод создает и возвращает объект типа Connection,
     * который позволит подключиться физически к БД
     * @return dataSource.getConnection()
     */
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
