package ru.homework.dao;

public abstract class DAOFactory {

    public static final int DATABASE = 1;
    public static final int FILE = 2;

    public abstract EmployeesDao getEmployeesDao();

    public static DAOFactory getDAOFactory(int concreteFactory) {
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
