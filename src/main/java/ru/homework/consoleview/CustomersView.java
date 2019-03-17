package ru.homework.consoleview;


import ru.homework.dao.entity.Customers;
import ru.homework.exceptions.NotUniqueIdException;
import ru.homework.exceptions.NotUniqueNameException;
import ru.homework.service.Service;

/**
 * Класс имплементирует интерфейс View для объекта Customers
 * Содержит методы для вызова CRUD событий объекта Customers
 */
public class CustomersView implements View {

    private Service<Customers> service;

    @Override
    public void setService(Service service){
        this.service = service;
    }

    @Override
    public void fireEventCreate() throws NotUniqueNameException, NotUniqueIdException {

    }

    @Override
    public void fireEventGetById() {

    }

    @Override
    public void fireEventGetAll() {

    }

    @Override
    public void fireEventDelete() {

    }

    @Override
    public void fireEventGetByName() {

    }

    @Override
    public void fireEventGetCount() {

    }

    @Override
    public void listOfCount() {

    }
}
