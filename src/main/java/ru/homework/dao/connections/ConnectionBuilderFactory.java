package ru.homework.dao.connections;

public class ConnectionBuilderFactory {

    public static ConnectionBuilder getConnectionBuilder() {
        return new ComboConnectionBuilder();
    }
}
