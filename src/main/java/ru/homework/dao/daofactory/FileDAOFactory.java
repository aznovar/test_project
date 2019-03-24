package ru.homework.dao.daofactory;

import ru.homework.dao.EmployeesDao;
import ru.homework.dao.implementations.EmployeesCSVImpl;

public class FileDAOFactory implements DAOFactory {

    /**
     * Метод, используемый при реализации объекта EmployeesDao,
     * при использовании файла содержащего данные ссылается на имплементацию интерфейса EmployeesDao
     *
     * @return
     */
    public EmployeesDao getEmployeesDao() {
        return new EmployeesCSVImpl();
    }
}
