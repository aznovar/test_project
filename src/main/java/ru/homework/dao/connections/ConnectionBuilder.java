package ru.homework.dao.connections;

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
