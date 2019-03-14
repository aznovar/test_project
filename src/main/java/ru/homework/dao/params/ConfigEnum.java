package ru.homework.dao.params;

public enum ConfigEnum {

   CONFIG_NAME("contacts.properties");

   private String contactProperties;

    ConfigEnum(String contProp) {
        this.contactProperties = contactProperties;
    }

    public String getContProp() {
        return contactProperties;
    }
}
