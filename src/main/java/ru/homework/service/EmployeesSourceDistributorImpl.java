package ru.homework.service;

import org.apache.log4j.Logger;
import ru.homework.consoleview.CommandHelper;
import ru.homework.dao.EmployeesDao;
import ru.homework.dao.daofactory.DAOFactory;

/**
 * Класс, имплементирующий интерфейс SourceDistributor для EmployeesDao.
 * Позволяет выбрать источник данных, который будет использоваться имплементацией EmployeesDao
 */
public class EmployeesSourceDistributorImpl implements SourceDistributor<EmployeesDao> {

    private static final Logger log = Logger.getLogger(CommandHelper.class);
    private EmployeesDao employeesDao;

    @Override
    public EmployeesDao chooseTheSource(int source) {
        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(source);
            if (daoFactory.getEmployeesDao() != null) {
                employeesDao = daoFactory.getEmployeesDao();
            }
            return employeesDao;
        } catch (NullPointerException e) {
            log.error(e);
        }
        return null;
    }
}
