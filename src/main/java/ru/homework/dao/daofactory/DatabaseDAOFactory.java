package ru.homework.dao.daofactory;

import ru.homework.dao.EmployeesDao;
import ru.homework.dao.config.GlobalConfigDatabase;

/**
 * Фабрика для создания экземпляра EmployeesDao
 */
public class DatabaseDAOFactory implements DAOFactory {

    /**
     * Метод, используемый при реализации объекта EmployeesDao,
     * при использовании базы данных ссылается на имплементацию интерфейса EmployeesDao,
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

