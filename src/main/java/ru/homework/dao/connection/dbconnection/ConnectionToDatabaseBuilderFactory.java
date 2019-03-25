package ru.homework.dao.connection.dbconnection;

import java.sql.SQLException;

/**
 * Данный класс служит для инстанса объектов реализаций интерфейса ConnectionToDatabaseBuilder
 */
public final class ConnectionToDatabaseBuilderFactory {

    private ConnectionToDatabaseBuilderFactory() {

    }

    public static ConnectionToDatabaseBuilder getMySQLConnectionBuilder() {
        return new MySQLConnectionBuilder();
    }

    public static void connectionClose() throws SQLException {
        getMySQLConnectionBuilder().getConnection().close();
    }
}
