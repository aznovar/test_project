package ru.homework.service;

import ru.homework.exceptions.NoSuchIdException;
import ru.homework.exceptions.NoSuchNameException;
import ru.homework.exceptions.NotUniqueIdException;
import ru.homework.exceptions.NotUniqueNameException;

import java.util.List;

/**
 * Интерфейс содержит методы для обработки CRUD событий
 *
 * @param <T>
 */
public interface Service<T> {

    /**
     * Метод вызываемый для добавления сотрудника в БД
     *
     * @param obj
     * @throws NotUniqueNameException
     * @throws NotUniqueIdException
     */
    void createCustomer(T obj) throws NotUniqueNameException, NotUniqueIdException;

    /**
     * Метод используется для нахождения сотрудника в базе по ID
     *
     * @param id
     * @return
     * @throws NoSuchIdException
     */
    T getCustomerById(Integer id) throws NoSuchIdException;

    /**
     * Метод для получения списка всех сотрудников
     *
     * @return
     */
    List<T> getAll();

    /**
     * Метод используемый для удаления сотрудника из базы
     *
     * @param id
     */
    void removeCustomer(Integer id);

    /**
     * Метод используется для поиска сотрудника в базе по имени
     *
     * @param name
     * @return
     * @throws NoSuchNameException
     */
    T getCustomerByName(String name) throws NoSuchNameException;


    /**
     * Метод для определения числа сотрудников в требуемой комнате
     *
     * @param roomNumber
     * @return
     */
    Integer countOfCustomersInRoom(Integer roomNumber);

    List<T> listOfCountCustomersInRoom();
}
