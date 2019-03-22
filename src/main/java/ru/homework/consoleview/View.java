package ru.homework.consoleview;

import ru.homework.exceptions.NotUniqueIdException;
import ru.homework.exceptions.NotUniqueNameException;
import ru.homework.service.Service;

import java.nio.file.NoSuchFileException;

/**
 * Интерфейс для определения экранного представления
 * запросов по поиску\добавлению\удалению сотрудников
 */
public interface View {

    /**
     * Метод для задания конкретной сущности
     * наполнение которой мы будем выводить на экран
     *
     * @param service
     */
    void setService(Service service);

    /**
     * Представление добавления сотрудника
     *
     * @throws NotUniqueNameException
     * @throws NotUniqueIdException
     */
    void fireEventCreate() throws NotUniqueNameException, NotUniqueIdException, NoSuchFileException;

    /**
     * Представление поиска сотрудника по id
     */
    void fireEventGetById();

    /**
     * Представление вывода списка сотрудников
     */
    void fireEventGetAll() throws NoSuchFileException;

    /**
     * Представление удаления сотрудника из базы
     */
    void fireEventDelete();

    /**
     * Представление поиска сотрудника по имени
     */
    void fireEventGetByName();

    /**
     * Представление вывода числа сотрудников в комнате
     */
    void fireEventGetCount();

    void listOfCount();
}
