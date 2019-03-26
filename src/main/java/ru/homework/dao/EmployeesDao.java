package ru.homework.dao;

import ru.homework.dao.entity.Employees;
import ru.homework.exceptions.NoSuchRoomNumberException;
import ru.homework.exceptions.NoSuchNameException;
import ru.homework.exceptions.NotUniqueIdException;
import ru.homework.exceptions.NotUniqueNameException;

import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.List;

/**
 * Интерфейс для определения методов взаимодействия с хранилищем данных
 */

public interface EmployeesDao {

    /**
     * Метод по добавлению сотрудника
     *
     * @param employees
     */
    void insertEmployees(Employees employees) throws NotUniqueIdException, NotUniqueNameException;

    /**
     * Метод для поиска сотрудников по номеру комнаты
     *
     * @param roomNumber
     */
    List<Employees> selectEmployeeByRoomNumber(Long roomNumber) throws NoSuchRoomNumberException;

    /**
     * Метод по получению списка всех сотрудников
     *
     * @return List<Employees>
     */
    List<Employees> selectAll() throws NoSuchFileException;

    /**
     * Метод по удалению сотрудника
     *
     * @param id
     */
    void deleteEmployee(Integer id);

    /**
     * Метод по получению сотрудника по имени
     *
     * @param name
     */
    List<Employees> selectEmployeeByName(String name) throws NoSuchNameException;

    /**
     * Метод по получению числа сотрудников в введенном номере комнаты
     *
     * @param roomNumber
     */
    Long countOfEmployeeInRoom(Long roomNumber);


    /**
     * Получение списка комнат и числа сотрудников в них
     *
     * @return List<Employees>
     */
    List<HashMap<String, Object>> listOfRoomNumbersAndEmployeesInIt(Long roomNumber);

}
