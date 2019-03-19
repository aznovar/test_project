package ru.homework.consoleview;


import ru.homework.dao.entity.Customers;
import ru.homework.exceptions.NoSuchIdException;
import ru.homework.exceptions.NoSuchNameException;
import ru.homework.exceptions.NotUniqueIdException;
import ru.homework.exceptions.NotUniqueNameException;
import ru.homework.service.Service;

import java.io.IOException;
import java.util.List;

/**
 * Класс имплементирует интерфейс View для объекта Customers
 * Содержит методы для вызова CRUD событий объекта Customers
 */
public class CustomersView implements View {

    private Service<Customers> service;

    @Override
    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public void fireEventCreate() throws NotUniqueNameException, NotUniqueIdException {
        service.createCustomer(createCustomer());
    }

    @Override
    public void fireEventGetById() {
        CommandHelper.writeToConsole("Введите id нужного вам сотрудника");
        try {
            int id = Integer.parseInt(CommandHelper.readString());
            singleSearch(service.getCustomerById(id));
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
            singleSearch(service.getCustomerByName(name));
            return;
        } catch (IOException e) {
            CommandHelper.writeToConsole("Некорректное имя. Попробуйте еще раз.\n");
        } catch (NoSuchNameException e) {
            CommandHelper.writeToConsole(e.getMessage());
        }
    }

    @Override
    public void fireEventGetAll() {
        writeAll(service.getAll());
    }

    @Override
    public void fireEventDelete() {
        while (true) {
            CommandHelper.writeToConsole("Input desired ID:");
            try {
                int id = Integer.parseInt(CommandHelper.readString());
                service.removeCustomer(id);
                return;
            } catch (IOException e) {
                CommandHelper.writeToConsole("Wrong ID. Try again.\n");
            }
        }
    }


    @Override
    public void fireEventGetCount() {
        CommandHelper.writeToConsole("Введите номер нужной вам комнаты");
        try{
            int roomNumber = Integer.parseInt(CommandHelper.readString());
            writeCount(service.countOfCustomersInRoom(roomNumber));
            return;
        }catch (IOException e){
            CommandHelper.writeToConsole("Неправильный номер комнаты. Попробуйте еще раз");
        }
    }

    @Override
    public void listOfCount() {

    }

    private Customers createCustomer() {
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
        return new Customers(id, name, roomNumber, salary, position);
    }

    private void singleSearch(Customers customers) {
        if (customers == null || (customers.getId() == 0 && customers.getName() == null)) {
            CommandHelper.writeToConsole("\nСотрудник с таким id не найден\n");
        } else {
            CommandHelper.writeToConsole("\n" + customers.toString() + "\n");
        }
    }

    private void writeAll(List<Customers> list) {
        if (list.isEmpty()) {
            CommandHelper.writeToConsole("Нечего выводить");
        } else {
            CommandHelper.writeToConsole("Все записи из таблицы сотрудников");
           for (Customers customers : list){
               CommandHelper.writeToConsole(customers.toString());
           }
           CommandHelper.writeToConsole("\n");
        }
    }

    private void writeCount(Integer count){
        if(count == null){
            CommandHelper.writeToConsole("в комнате пусто");
        } else{
            CommandHelper.writeToConsole("Число сотрудников в комнате: \n" + count.toString());
        }
    }
}
