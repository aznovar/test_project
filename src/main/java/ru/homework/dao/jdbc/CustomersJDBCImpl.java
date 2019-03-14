package ru.homework.dao.jdbc;

import ru.homework.dao.CustomersDao;
import ru.homework.dao.model.Customers;


import java.util.List;

public class CustomersJDBCImpl implements CustomersDao {

    @Override
    public Integer insertCustomer(Customers customers) {
        Integer id = 4;
        return id;
    }

    @Override
    public Customers selectCustById(Integer id) {
        return new Customers();
    }

    @Override
    public List<Customers> selectAll() {
        return null;
    }

    @Override
    public void deleteCustomer(Integer id) {

    }

    @Override
    public Customers selectCustomerByName(String name) {
        return null;
    }

    @Override
    public Integer countOfCustomersInRoom(Integer roomNumber) {
        return null;
    }

    @Override
    public List<Customers> listOfCountCustomersInRoom() {
        return null;
    }
}
