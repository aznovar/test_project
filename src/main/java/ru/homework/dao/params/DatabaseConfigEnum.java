package ru.homework.dao.params;

/**
 * Enum содержащий в себе параметры для
 * построения конфигурации подключения к бд
 */
public enum DatabaseConfigEnum {

    CONFIG_NAME("src/main/database.properties");

    private String databaseProperties;

    DatabaseConfigEnum(String databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    public String getContProp() {
        return databaseProperties;
    }
}
