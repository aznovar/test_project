package ru.homework.dao;

import ru.homework.dao.jdbc.CustomersJDBCImpl;

/**
 * Фабрика для создания экземпляра CustomersDao
 */
public class CustomersDAOFactory {

    public static CustomersDao getCustomersDao() {

        return new CustomersJDBCImpl();
    }
}
