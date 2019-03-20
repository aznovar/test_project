package ru.homework.dao.params;

/**
 * Enum содержащий в себе параметры для
 * построения конфигурации подключения к бд
 */
public enum ConfigEnum {

    CONFIG_NAME("src/main/database.properties");

    private String databaseProperties;

    ConfigEnum(String databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    public String getContProp() {
        return databaseProperties;
    }
}
