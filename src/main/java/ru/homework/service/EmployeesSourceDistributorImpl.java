package ru.homework.service;

import ru.homework.dao.EmployeesDao;
import ru.homework.dao.daofactory.DAOFactory;

public class EmployeesSourceDistributorImpl implements SourceDistributor<EmployeesDao> {

    private EmployeesDao employeesDao;

    @Override
    public EmployeesDao chooseTheSource(int source) throws NullPointerException {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(source);
        if(daoFactory.getEmployeesDao() != null) {
            employeesDao = daoFactory.getEmployeesDao();
        }
        return employeesDao;
    }
}
