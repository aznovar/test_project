package ru.homework.dao;

import ru.homework.dao.model.Customers;

import java.util.List;

public interface CustomersDao {

    public void createDeveloper(String name, Integer roomNumber, Integer salary, String position);

    public void getCustById(Integer id);

    public List<Customers> getAll();

    public void removeCustomer(Integer id);

    public void getCustomerByName(String name);

    public void countOfCustomersInRoom(Integer roomNumber);

    public List<Customers> listOfCountCustomersInRoom();

}
