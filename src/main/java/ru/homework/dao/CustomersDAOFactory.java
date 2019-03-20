package ru.homework.dao;

import ru.homework.dao.config.GlobalConfig;
import ru.homework.dao.jdbc.CustomersJDBCImpl;

/**
 * Фабрика для создания экземпляра CustomersDao
 */
public class CustomersDAOFactory {

    /**
     * Метод, используемый при реализации объекта CustomersDAO,
     * ссылается на имплементацию интерфейса CustomersDAO, указанную в проперти файле
     * возвращает объект класса имплементации DAO интерфейса
     *
     * @return
     */
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

