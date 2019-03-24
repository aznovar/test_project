package ru.homework.dao.daofactory;

import ru.homework.dao.EmployeesDao;

public interface DAOFactory {

    EmployeesDao getEmployeesDao();

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
