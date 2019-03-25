package ru.homework.dao.daofactory;

import ru.homework.dao.EmployeesDao;

/**
 * Абстрактная фабрика с возможностью генерации DAO объектов различного типа,
 * которые в свою очередь могут поддерживать различные типы реализаций хранилищ данных
 */
public interface DAOFactory {

    /**
     * Получение экземпляра EmployeesDao
     *
     * @return
     */
    EmployeesDao getEmployeesDao();

    /**
     * Статический метод для выбора реализации конкретного типа хранилища
     *
     * @param concreteFactory
     * @return
     */
    static DAOFactory getDAOFactory(int concreteFactory) {
        switch (concreteFactory) {
            case 1:
                return new DatabaseDAOFactory();
            case 2:
                return new FileDAOFactory();
            default:
                return null;
        }
    }
}
