package ru.homework.consoleview;


import ru.homework.dao.entity.Employees;
import ru.homework.exceptions.NoSuchIdException;
import ru.homework.exceptions.NoSuchNameException;
import ru.homework.exceptions.NotUniqueIdException;
import ru.homework.exceptions.NotUniqueNameException;
import ru.homework.service.Service;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

/**
 * Класс имплементирует интерфейс View для объекта Employees
 * Содержит методы для вызова CRUD событий объекта Employees
 */
public class EmployeesView implements View {

    private Service<Employees> service;

    @Override
    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public void fireEventCreate() throws NotUniqueNameException, NotUniqueIdException,NoSuchFileException {
        service.createEmployee(createCustomer());
    }

    @Override
    public void fireEventGetById() {
        CommandHelper.writeToConsole("Введите id нужного вам сотрудника:");
        try {
            int id = Integer.parseInt(CommandHelper.readString());
            singleSearch(service.getEmployeeById(id));
            return;
        } catch (IOException e) {
            CommandHelper.writeToConsole("Некорректный id. Попробуйте еще раз.\n");
        } catch (NoSuchIdException e) {
            CommandHelper.writeToConsole(e.getMessage());
        }
    }

    @Override
    public void fireEventGetByName() {
        CommandHelper.writeToConsole("Введите имя нужного вам сотрудника");
        try {
            String name = CommandHelper.readString();
            singleSearch(service.getEmployeeByName(name));
            return;
        } catch (IOException e) {
            CommandHelper.writeToConsole("Некорректное имя. Попробуйте еще раз.\n");
        } catch (NoSuchNameException e) {
            CommandHelper.writeToConsole(e.getMessage());
        }
    }

    @Override
    public void fireEventGetAll() throws NoSuchFileException {
        writeAll(service.getAll());
    }

    @Override
    public void fireEventDelete() {
        while (true) {
            CommandHelper.writeToConsole("Input desired ID:");
            try {
                int id = Integer.parseInt(CommandHelper.readString());
                service.removeEmployee(id);
                return;
            } catch (IOException e) {
                CommandHelper.writeToConsole("Wrong ID. Try again.\n");
            }
        }
    }


    @Override
    public void fireEventGetCount() {
        CommandHelper.writeToConsole("Введите номер нужной вам комнаты");
        try {
            int roomNumber = Integer.parseInt(CommandHelper.readString());
            writeCount(service.countOfEmployeeInRoom(roomNumber));
            return;
        } catch (IOException e) {
            CommandHelper.writeToConsole("Неправильный номер комнаты. Попробуйте еще раз");
        }
    }

    @Override
    public void listOfCount() {

    }

    private Employees createCustomer() {
        int id;
        String name;
        int roomNumber;
        int salary;
        String position;

        while (true) {
            try {
                CommandHelper.writeToConsole("Введите id сотрудника");
                id = Integer.parseInt(CommandHelper.readString());
                break;
            } catch (IOException | NumberFormatException e) {
                CommandHelper.writeToConsole("Wrong integer. Try again");
            }
        }
        while (true) {
            try {
                CommandHelper.writeToConsole("Введите имя сотрудника");
                name = CommandHelper.readString();
                break;
            } catch (IOException e) {
                CommandHelper.writeToConsole("Failed input. Try again");
            }
        }
        while (true) {
            try {
                CommandHelper.writeToConsole("Введите комнату сотрудника");
                roomNumber = Integer.parseInt(CommandHelper.readString());
                break;
            } catch (IOException | NumberFormatException e) {
                CommandHelper.writeToConsole("Wrong integer. Try again");
            }
        }
        while (true) {
            try {
                CommandHelper.writeToConsole("Введите зарплату сотрудника");
                salary = Integer.parseInt(CommandHelper.readString());
                break;
            } catch (IOException | NumberFormatException e) {
                CommandHelper.writeToConsole("Wrong integer. Try again");
            }
        }
        while (true) {
            try {
                CommandHelper.writeToConsole("Введите должность сотрудника");
                position = CommandHelper.readString();
                break;
            } catch (IOException e) {
                CommandHelper.writeToConsole("Failed input. Try again");
            }
        }
        return new Employees(id, name, roomNumber, salary, position);
    }

    //TODO выделить ниженаписанные методы в отдельную сущность SearchHelper, т.к. они не совсем вписываются в данный класс
    private void singleSearch(List<Employees> customers) {
        if (customers == null || (customers.get(0).getId()==0)) {
            CommandHelper.writeToConsole("\nСотрудник с таким id не найден\n");
        } else {
            CommandHelper.writeToConsole("\n" + customers + "\n");
        }
    }

    private void writeAll(List<Employees> list) {
        if (list.isEmpty()) {
            CommandHelper.writeToConsole("Нечего выводить");
        } else {
            CommandHelper.writeToConsole("Все записи из таблицы сотрудников");
            for (Employees employees : list) {
                CommandHelper.writeToConsole(employees.toString());
            }
            CommandHelper.writeToConsole("\n");
        }
    }

    private void writeCount(Integer count) {
        if (count == null) {
            CommandHelper.writeToConsole("в комнате пусто");
        } else {
            CommandHelper.writeToConsole("Число сотрудников в комнате: \n" + count.toString());
        }
    }
}
