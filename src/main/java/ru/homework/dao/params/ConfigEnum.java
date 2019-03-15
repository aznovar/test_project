package ru.homework.dao.params;

public enum ConfigEnum {

   CONFIG_NAME("src/main/database.properties");

   private String databaseProperties;

    ConfigEnum(String contProp) {
        this.databaseProperties = databaseProperties;
    }

    public String getContProp() {
        return databaseProperties;
    }
}
