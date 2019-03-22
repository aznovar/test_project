package ru.homework.dao;

import ru.homework.dao.config.GlobalConfigDatabase;

/**
 * Фабрика для создания экземпляра EmployeesDao
 */
public class DatabaseDAOFactory extends DAOFactory {

    /**
     * Метод, используемый при реализации объекта CustomersDAO,
     * при использовании базы данных ссылается на имплементацию интерфейса CustomersDAO,
     * указанную в проперти файле и возвращает объект класса имплементации DAO интерфейса
     *
     * @return
     */
    public EmployeesDao getEmployeesDao() {

        try {
            Class dao = Class.forName(GlobalConfigDatabase.getProperty("dao.class"));
            return (EmployeesDao) dao.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

