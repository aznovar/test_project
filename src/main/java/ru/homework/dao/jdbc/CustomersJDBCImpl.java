package ru.homework.dao.jdbc;

import ru.homework.dao.CustomersDao;
import ru.homework.dao.model.Customers;


import java.util.List;

public class CustomersJDBCImpl implements CustomersDao {

    @Override
    public void createDeveloper(String name, Integer roomNumber, Integer salary, String position) {

    }

    @Override
    public void getCustById(Integer id) {

    }

    @Override
    public List<Customers> getAll() {
        return null;
    }

    @Override
    public void removeCustomer(Integer id) {

    }

    @Override
    public void getCustomerByName(String name) {

    }

    @Override
    public void countOfCustomersInRoom(Integer roomNumber) {

    }

    @Override
    public List<Customers> listOfCountCustomersInRoom() {
        return null;
    }
}
