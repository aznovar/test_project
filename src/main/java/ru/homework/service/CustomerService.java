package ru.homework.service;

import ru.homework.dao.DAOFactory;
import ru.homework.dao.EmployeesDao;
import ru.homework.dao.entity.Employees;
import ru.homework.exceptions.NoSuchIdException;
import ru.homework.exceptions.NoSuchNameException;
import ru.homework.exceptions.NotUniqueIdException;
import ru.homework.exceptions.NotUniqueNameException;

import java.nio.file.NoSuchFileException;
import java.util.List;

public class CustomerService implements Service<Employees> {

    private EmployeesDao employeesDao;

    @Override
    public EmployeesDao chooseTheSource(int source) {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(source);
        employeesDao = daoFactory.getEmployeesDao();
         return employeesDao;
    }

    @Override
    public void createEmployee(Employees employees) throws NotUniqueNameException, NotUniqueIdException, NoSuchFileException {
        employeesDao.insertEmployees(employees);
    }

    @Override
    public List<Employees> getEmployeeById(Integer id) throws NoSuchIdException {
        return employeesDao.selectEmployeeById(id);
    }

    @Override
    public List<Employees> getAll() throws NoSuchFileException{
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
    public Integer countOfEmployeeInRoom(Integer roomNumber) {
        return employeesDao.countOfEmployeeInRoom(roomNumber);
    }

    @Override
    public List<Employees> listOfCountEmployeesInRoom() {
        return employeesDao.listOfEmployeeInRoom();
    }
}
