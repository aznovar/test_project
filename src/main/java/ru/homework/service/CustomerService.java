package ru.homework.service;

import ru.homework.dao.CustomersDAOFactory;
import ru.homework.dao.CustomersDao;
import ru.homework.dao.entity.Customers;
import ru.homework.exceptions.NoSuchIdException;
import ru.homework.exceptions.NoSuchNameException;
import ru.homework.exceptions.NotUniqueIdException;
import ru.homework.exceptions.NotUniqueNameException;

import java.util.List;

public class CustomerService implements Service<Customers> {

    private CustomersDao customersDao;

    /**
     * Создает сущность имплементированного интерфейса DAO
     */
    public CustomerService() {
        customersDao = CustomersDAOFactory.getCustomersDao();
    }

    @Override
    public void createCustomer(Customers customers) throws NotUniqueNameException, NotUniqueIdException {
        customersDao.insertCustomer(customers);
    }

    @Override
    public Customers getCustomerById(Integer id) throws NoSuchIdException {
        return customersDao.selectCustById(id);
    }

    @Override
    public List<Customers> getAll() {
        return customersDao.selectAll();
    }

    @Override
    public void removeCustomer(Integer id) {
        customersDao.deleteCustomer(id);
    }

    @Override
    public Customers getCustomerByName(String name) throws NoSuchNameException {
        return customersDao.selectCustomerByName(name);
    }

    @Override
    public Integer countOfCustomersInRoom(Integer roomNumber) {
        return customersDao.countOfCustomersInRoom(roomNumber);
    }

    @Override
    public List<Customers> listOfCountCustomersInRoom() {
        return customersDao.listOfCountCustomersInRoom();
    }
}
