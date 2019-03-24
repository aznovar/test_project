package ru.homework.service;

import ru.homework.dao.daofactory.DAOFactory;
import ru.homework.dao.EmployeesDao;
import ru.homework.dao.entity.Employees;
import ru.homework.exceptions.NoSuchIdException;
import ru.homework.exceptions.NoSuchNameException;
import ru.homework.exceptions.NotUniqueIdException;
import ru.homework.exceptions.NotUniqueNameException;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.List;

public class EmployeesService implements Service<Employees>, SourceDistributor<EmployeesDao> {

    private EmployeesDao employeesDao;

    public static Service getExemplarOfEmployeesService() {
        return new EmployeesService();
    }

    @Override
    public EmployeesDao chooseTheSource(int source) {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(source);
        if (daoFactory.getEmployeesDao() != null) {
            employeesDao = daoFactory.getEmployeesDao();
        }
        return employeesDao;
    }

    @Override
    public void createEmployee(Employees employees) throws IOException, NotUniqueNameException, NotUniqueIdException {
        employeesDao.insertEmployees(employees);
    }

    @Override
    public List<Employees> getEmployeeById(Integer id) throws NoSuchIdException {
        return employeesDao.selectEmployeeById(id);
    }

    @Override
    public List<Employees> getAll() throws NoSuchFileException {
        return employeesDao.selectAll();
    }

    @Override
    public void removeEmployee(Integer id) {
        employeesDao.deleteEmployee(id);
    }

    @Override
    public List<Employees> getEmployeeByName(String name) throws NoSuchNameException {
        return employeesDao.selectEmployeeByName(name);
    }

    @Override
    public Long countOfEmployeeInRoom(Long roomNumber) {
        return employeesDao.countOfEmployeeInRoom(roomNumber);
    }

    @Override
    public List<HashMap<String, Object>> listOfCountEmployeesInRoom(Long roomNumber) {
        return employeesDao.listOfRoomNumbersAndEmployeesInIt(roomNumber);
    }
}
