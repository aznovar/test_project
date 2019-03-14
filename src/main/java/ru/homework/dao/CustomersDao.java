package ru.homework.dao;

import ru.homework.dao.model.Customers;

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
     Integer insertCustomer(Customers customers);

    /**
     * Получение сотрудника по id
     *
     * @param id
     */
     Customers selectCustById(Integer id);

    /**
     * Получение списка всех сотрудников
     *
     * @return
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
     Customers selectCustomerByName(String name);

    /**
     * Получение числа сотрудников по номеру комнаты
     * @param roomNumber
     */
     Integer countOfCustomersInRoom(Integer roomNumber);


    /**
     * Получение списка комнат и числа сотрудников в них
     * @return
     */
     List<Customers> listOfCountCustomersInRoom();

}
