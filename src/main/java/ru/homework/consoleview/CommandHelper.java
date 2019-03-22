package ru.homework.consoleview;

import ru.homework.dao.connections.ConnectionBuilderFactory;
import ru.homework.exceptions.NotUniqueIdException;
import ru.homework.exceptions.NotUniqueNameException;
import ru.homework.service.CustomerService;
import ru.homework.service.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class CommandHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private View view;
    private Service service;


    /**
     * Основной метод для запуска приложения
     */
    public void startApp() {
        showMainMenu();
    }

    /**
     * Метод позволяющий взаимодействовать с БД
     * на уровне основных команд таких как
     * Начать работу или выйти
     **/
    private void showMainMenu() {
        while (true) {
            try {
                writeToConsole("\nВведите команду:\n\n" +
                        "0 - Начать работу с базой сотрудников\n" +
                        "1 - Начать работу с данными из csv файла\n" +
                        "2 - Выйти\n");
                switch (Integer.parseInt(readString())) {
                    case 0:
                        view = new EmployeesView();
                        service = new CustomerService();
                        service.chooseTheSource(1);
                        view.setService(service);
                        showBaseCommand();
                        break;
                    case 1:
                        view = new EmployeesView();
                        service = new CustomerService();
                        service.chooseTheSource(2);
                        view.setService(service);
                        showBaseCommand();
                        break;
                    case 2:
                        ConnectionBuilderFactory.connectionClose();
                        return;
                    default:
                        throw new IllegalArgumentException();
                }
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                writeToConsole("Wrong select. Try again.");
            }
        }
    }

    /**
     * Метод позволяющий взаимодействовать с БД на уровне требуемых запросов
     */
    private void showBaseCommand() {
        while (true) {
            try {
                writeToConsole("\nВыберите команду:\n\n" +
                        "0 - Добавить сотрудника\n" +
                        "1 - Найти сотрудника по id\n" +
                        "2 - Найти сотрудника по имени\n" +
                        "3 - Вывести список всех сотрудников\n" +
                        "4 - Удалить сотрудника из базы\n" +
                        "5 - Вывести число сотрудников в комнате\n" +
                        "6 - Назад\n");
                switch (Integer.parseInt(readString())) {
                    case 0:
                        view.fireEventCreate();
                        break;
                    case 1:
                        view.fireEventGetById();
                        break;
                    case 2:
                        view.fireEventGetByName();
                        break;
                    case 3:
                        view.fireEventGetAll();
                        break;
                    case 4:
                        view.fireEventDelete();
                        break;
                    case 5:
                        view.fireEventGetCount();
                        break;
                    case 6:
                        return;
                    default:
                        throw new IllegalArgumentException();
                }
            } catch (IOException | NotUniqueNameException | NotUniqueIdException e) {
                writeToConsole(e.getMessage());
            } catch (IllegalArgumentException e) {
                writeToConsole(e.getMessage());
                writeToConsole("Wrong command. Try again.");
            }
        }
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static void writeToConsole(String message) {
        System.out.println(message);
    }
}
