package ru.homework.dao;

import ru.homework.dao.entity.Customers;
import ru.homework.exceptions.NoSuchIdException;
import ru.homework.exceptions.NoSuchNameException;
import ru.homework.exceptions.NotUniqueIdException;
import ru.homework.exceptions.NotUniqueNameException;

import java.util.List;

/**
 * Интерфейс для определения методов взаимодействия с хранилищем данных
 */

public interface CustomersDao {

    /**
     * Добавление сотрудника
     *
     * @param customers
     */
     void insertCustomer(Customers customers) throws NotUniqueIdException, NotUniqueNameException;

    /**
     * Получение сотрудника по id
     *
     * @param id
     */
     Customers selectCustById(Integer id) throws NoSuchIdException;

    /**
     * Получение списка всех сотрудников
     *
     * @return List<Customers>
     */
     List<Customers> selectAll();

    /**
     * Удаление сотрудника
     *
     * @param id
     */
     void deleteCustomer(Integer id);

    /**
     * Получение сотрудника по имени
     *
     * @param name
     */
     Customers selectCustomerByName(String name) throws NoSuchNameException;

    /**
     * Получение числа сотрудников по номеру комнаты
     * @param roomNumber
     */
     Integer countOfCustomersInRoom(Integer roomNumber);


    /**
     * Получение списка комнат и числа сотрудников в них
     * @return List<Customers>
     */
     List<Customers> listOfCountCustomersInRoom();

}
