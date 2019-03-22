package ru.homework.dao.connections;

import java.sql.SQLException;

public final class ConnectionBuilderFactory {

    private ConnectionBuilderFactory() {

    }

    public static ConnectionBuilder getConnectionBuilder() {
        return new MySQLConnectionBuilder();
    }

    public static void connectionClose() throws SQLException {
        getConnectionBuilder().getConnection().close();
    }
}
