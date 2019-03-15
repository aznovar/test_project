package ru.homework.service;

import ru.homework.exceptions.NoSuchIdException;
import ru.homework.exceptions.NotUniqueIdException;
import ru.homework.exceptions.NotUniqueNameException;

import java.util.List;

/**
 * Интерфейс содержит методы для обработки CRUD событий
 *
 * @param <T>
 */
public interface Service<T> {

    Integer createCustomer(T obj) throws NotUniqueNameException, NotUniqueIdException;

    T getCustomerById(Integer id) throws NoSuchIdException;

    List<T> getAll();

    void removeCustomer(Integer id);

    T getCustomerByName(String name) throws NotUniqueNameException;

    Integer countOfCustomersInRoom(Integer roomNumber);

    List<T> listOfCountCustomersInRoom();
}
