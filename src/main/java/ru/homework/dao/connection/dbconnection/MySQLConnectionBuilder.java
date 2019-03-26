package ru.homework.dao.connection.dbconnection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;
import ru.homework.consoleview.ConsoleComand;
import ru.homework.dao.config.GlobalConfigDatabase;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Класс реализующий интерфейс ConnectionToDatabaseBuilder
 * Позволяет, на основе параметров, подключиться к базе данных
 * При подключении используется интерфейс DataSource
 * В качестве БД - MySQL
 */
public class MySQLConnectionBuilder implements ConnectionToDatabaseBuilder {

    private static final Logger log = Logger.getLogger(ConsoleComand.class);
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
            log.error(e);
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
