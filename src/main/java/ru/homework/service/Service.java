package ru.homework.service;

import ru.homework.dao.model.Customers;

import java.util.List;

/**
 * Интерфейс содержит методы для обработки CRUD событий
 *
 * @param <T>
 */
public interface Service<T> {

    Integer createCustomer(Customers customers);

    Customers getCustomerById(Integer id);

    List<Customers> getAll();

    void removeCustomer(Integer id);

    Customers getCustomerByName(String name);

    Integer countOfCustomersInRoom(Integer roomNumber);

    List<Customers> listOfCountCustomersInRoom();
}
