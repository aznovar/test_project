package ru.homework.dao.connection;

import ru.homework.dao.connection.dbconnection.ConnectionToDatabaseBuilder;
import ru.homework.dao.connection.dbconnection.MySQLConnectionBuilder;
import ru.homework.dao.connection.fileconnection.CsvConnectionBuilder;
import ru.homework.dao.connection.fileconnection.FileConnectionBuilder;

import java.sql.SQLException;

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
