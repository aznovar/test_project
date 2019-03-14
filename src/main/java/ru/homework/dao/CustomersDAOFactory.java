package ru.homework.dao;

import ru.homework.dao.config.GlobalConfig;
import ru.homework.dao.jdbc.CustomersJDBCImpl;

/**
 * Фабрика для создания экземпляра CustomersDao
 */
public class CustomersDAOFactory {

    public static CustomersDao getCustomersDao() {

        try {
            Class dao = Class.forName(GlobalConfig.getProperty("dao.class"));
            return (CustomersDao) dao.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

