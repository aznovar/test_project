package ru.homework.dao.jdbc;

import ru.homework.dao.CustomersDao;
import ru.homework.dao.model.Customers;
import ru.homework.exceptions.NoSuchIdException;
import ru.homework.exceptions.NotUniqueIdException;
import ru.homework.exceptions.NotUniqueNameException;


import java.util.List;

public class CustomersJDBCImpl implements CustomersDao {

    @Override
    public Integer insertCustomer(Customers customers) throws NotUniqueIdException, NotUniqueNameException {
        Integer id = 4;
        return id;
    }

    @Override
    public Customers selectCustById(Integer id) throws NoSuchIdException {
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
    public Customers selectCustomerByName(String name) throws NotUniqueNameException {
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
