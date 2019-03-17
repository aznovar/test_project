package ru.homework.dao.connections;

import java.sql.SQLException;

public class ConnectionBuilderFactory {

    public static ConnectionBuilder getConnectionBuilder() {
        return new ComboConnectionBuilder();
    }

    public static void connectionClose() throws SQLException {
        getConnectionBuilder().getConnection().close();
    }
}
