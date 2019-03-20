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
     * Метод по добавлению сотрудника
     *
     * @param customers
     */
    void insertCustomer(Customers customers) throws NotUniqueIdException, NotUniqueNameException;

    /**
     * Метод по получению сотрудника по id
     *
     * @param id
     */
    Customers selectCustById(Integer id) throws NoSuchIdException;

    /**
     * Метод по получению списка всех сотрудников
     *
     * @return List<Customers>
     */
    List<Customers> selectAll();

    /**
     * Метод по удалению сотрудника
     *
     * @param id
     */
    void deleteCustomer(Integer id);

    /**
     * Метод по получению сотрудника по имени
     *
     * @param name
     */
    Customers selectCustomerByName(String name) throws NoSuchNameException;

    /**
     * Метод по получению числа сотрудников в введенном номере комнаты
     *
     * @param roomNumber
     */
    Integer countOfCustomersInRoom(Integer roomNumber);


    /**
     * Получение списка комнат и числа сотрудников в них
     *
     * @return List<Customers>
     */
    List<Customers> listOfCountCustomersInRoom();

}
