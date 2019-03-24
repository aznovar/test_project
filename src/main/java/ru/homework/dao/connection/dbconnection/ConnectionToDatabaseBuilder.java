package ru.homework.dao.connection.dbconnection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionToDatabaseBuilder {

    /**
     * Метод для получения соединения с БД
     *
     * @return
     * @throws SQLException
     */
    Connection getConnection() throws SQLException;
}
