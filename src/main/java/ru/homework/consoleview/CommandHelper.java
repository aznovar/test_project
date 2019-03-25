package ru.homework.consoleview;

import org.apache.log4j.Logger;
import ru.homework.dao.connection.ConnectionToDatabaseBuilderFactory;
import ru.homework.exceptions.NotUniqueIdException;
import ru.homework.exceptions.NotUniqueNameException;
import ru.homework.service.EmployeesService;
import ru.homework.service.EmployeesSourceDistributorImpl;
import ru.homework.service.Service;
import ru.homework.service.SourceDistributor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

/**
 * Класс содержит основные методы для отображения на консоли результатов поиска(запроса)
 * по БД\файлу\etc
 */
public class CommandHelper {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private View view;
    private static final Logger log = Logger.getLogger(CommandHelper.class);


    /**
     * Основной метод для запуска приложения
     */
    public void startApp() {
        showMainMenu();
    }

    /**
     * Метод позволяющий взаимодействовать с источником данных
     * на уровне основных команд таких как:
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
                        SourceDistributor sourceDistributor = new EmployeesSourceDistributorImpl();
                        Service service = new EmployeesService(sourceDistributor, 1);
                        view.setService(service);
                        showBaseCommand();
                        break;
                    case 1:
                        view = new EmployeesView();
                        sourceDistributor = new EmployeesSourceDistributorImpl();
                        service = new EmployeesService(sourceDistributor, 2);
                        view.setService(service);
                        showBaseCommand();
                        break;
                    case 2:
                        return;
                    default:
                        throw new IllegalArgumentException();
                }
            } catch (IOException e) {
                log.error(e);
            } catch (IllegalArgumentException e) {
                writeToConsole("Wrong select. Try again.");
            }
        }
    }

    /**
     * Метод позволяющий взаимодействовать с выбранным источником на уровне требуемых запросов
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
                        "6 - Вывести список комнат с числом сотрудников в них\n" +
                        "7 - Вернуться к выбору источника данных, выйдя из текущего источника\n");
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
                        view.listOfCount();
                        break;
                    case 7:
                        ConnectionToDatabaseBuilderFactory.connectionClose();
                        return;
                    default:
                        throw new IllegalArgumentException();
                }
            } catch (IOException | NotUniqueNameException | NotUniqueIdException | SQLException e) {
                writeToConsole(e.getMessage());
            } catch (IllegalArgumentException e) {
                writeToConsole(e.getMessage());
                writeToConsole("Неправильная команда. Попробуйте еще раз");
            }
        }
    }

    private static String readString() throws IOException {
        return reader.readLine();
    }

    private static void writeToConsole(String message) {
        System.out.println(message);
    }
}
