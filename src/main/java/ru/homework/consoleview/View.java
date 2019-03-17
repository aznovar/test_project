package ru.homework.consoleview;

import ru.homework.exceptions.NotUniqueIdException;
import ru.homework.exceptions.NotUniqueNameException;
import ru.homework.service.Service;

public interface View {

    void setService(Service service);

    void fireEventCreate() throws NotUniqueNameException, NotUniqueIdException;

    void fireEventGetById();

    void fireEventGetAll();

    void fireEventDelete();

    void fireEventGetByName();

    void fireEventGetCount();

    void listOfCount();
}
