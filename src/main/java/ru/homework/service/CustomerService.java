package ru.homework.service;

import ru.homework.dao.CustomersDAOFactory;
import ru.homework.dao.CustomersDao;
import ru.homework.dao.model.Customers;

import java.security.PublicKey;
import java.util.List;

public class CustomerService implements Service<Customers> {

    private CustomersDao customersDao;

    public CustomerService() {
        customersDao = CustomersDAOFactory.getCustomersDao();
    }

    @Override
    public Integer createCustomer(Customers customers) {
        return customersDao.insertCustomer(customers);
    }

    @Override
    public Customers getCustomerById(Integer id) {
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
    public Customers getCustomerByName(String name) {
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
