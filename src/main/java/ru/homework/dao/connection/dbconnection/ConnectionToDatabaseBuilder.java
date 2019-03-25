package ru.homework.dao.connection.dbconnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Этот интерфейс служит для того, чтобы при его использовании
 * можно было получить соединение к БД, без привязки к конкретному типу базы данных
 */
public interface ConnectionToDatabaseBuilder {

    /**
     * Метод для получения соединения с БД
     *
     * @return
     * @throws SQLException
     */
    Connection getConnection() throws SQLException;
}
