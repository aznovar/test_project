package ru.homework.consoleview;


import org.apache.log4j.Logger;
import ru.homework.consoleview.helpers.EmployeesEntryHelper;
import ru.homework.consoleview.helpers.EmployeesOutputHelper;
import ru.homework.consoleview.helpers.EntryHelper;
import ru.homework.consoleview.helpers.OutputHelper;
import ru.homework.dao.entity.Employees;
import ru.homework.exceptions.NoSuchIdException;
import ru.homework.exceptions.NoSuchNameException;
import ru.homework.exceptions.NotUniqueIdException;
import ru.homework.exceptions.NotUniqueNameException;
import ru.homework.service.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.NoSuchFileException;


/**
 * Класс имплементирует интерфейс View для объекта Employees
 * Содержит методы для вызова CRUD событий объекта Employees
 */
public class EmployeesView implements View {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final Logger log = Logger.getLogger(CommandHelper.class);
    private Service<Employees> service;
    private OutputHelper<Employees> outputHelper = new EmployeesOutputHelper();
    private EntryHelper<Employees> entryHelper = new EmployeesEntryHelper();

    @Override
    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public void fireEventCreate() throws NotUniqueNameException, NotUniqueIdException {
        service.createEmployee(entryHelper.manualEntitiesParameterEntry());
    }

    @Override
    public void fireEventGetById() {
        System.out.println("Введите id нужного вам сотрудника:");
        try {
            int id = Integer.parseInt(reader.readLine());
            outputHelper.outputAfterSingleSearch(service.getEmployeeById(id));
        } catch (IOException e) {
            System.out.println("Некорректный id. Попробуйте еще раз.\n");
        } catch (NoSuchIdException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void fireEventGetByName() {
        System.out.println("Введите имя нужного вам сотрудника");
        try {
            String name = reader.readLine();
            outputHelper.outputAfterSingleSearch(service.getEmployeeByName(name));
        } catch (NoSuchNameException e) {
            System.out.println("Некорректное имя. Попробуйте еще раз.\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void fireEventGetAll() throws NoSuchFileException {
        outputHelper.outputAll(service.getAll());
    }

    @Override
    public void fireEventDelete() {
        while (true) {
            System.out.println("Input desired ID:");
            try {
                int id = Integer.parseInt(reader.readLine());
                service.removeEmployee(id);
                return;
            } catch (IOException e) {
                System.out.println("Неправильный ID. Попробуйте еще раз\n");
            }
        }
    }


    @Override
    public void fireEventGetCount() {
        System.out.println("Введите номер нужной вам комнаты");
        try {
            Long roomNumber = Long.parseLong(reader.readLine());
            outputHelper.outputCount(service.countOfEmployeeInRoom(roomNumber));
        } catch (IOException e) {
            System.out.println("Неправильный номер комнаты. Попробуйте еще раз");
        }
    }

    @Override
    public void listOfCount() {
        System.out.println("Введите количество комнат в которых нужно посчитать сотрудников");
        try {
            Long roomNumber = Long.parseLong(reader.readLine());
            outputHelper.outputPairwise(service.listOfCountEmployeesInRoom(roomNumber));
        } catch (IOException e) {
            log.error(e);
        }
    }


}
