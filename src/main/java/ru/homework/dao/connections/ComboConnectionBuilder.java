package ru.homework.dao.connections;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import ru.homework.dao.config.GlobalConfig;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Класс реализующий интерфейс ConnectionBuilder
 * Позволяет, на основе параметров, подключиться к базе данных
 * При подключении используется интерфейс DataSource
 */
public class ComboConnectionBuilder implements ConnectionBuilder {

    private ComboPooledDataSource dataSource;

    /**
     * Конструктор класса ComboConnectionBuilder.
     * Устанавливает соответствующие параметры для соединения,
     * которые берутся из проперти файла database.properties
     */
    public ComboConnectionBuilder() {
        try {
            dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(GlobalConfig.getProperty("db.driver.class"));
            dataSource.setJdbcUrl(GlobalConfig.getProperty("db.url"));
            dataSource.setUser(GlobalConfig.getProperty("db.login"));
            dataSource.setPassword(GlobalConfig.getProperty("db.password"));
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
