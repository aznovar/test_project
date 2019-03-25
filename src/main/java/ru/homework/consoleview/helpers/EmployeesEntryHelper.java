package ru.homework.consoleview.helpers;

import ru.homework.dao.entity.Employees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EmployeesEntryHelper implements EntryHelper<Employees> {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public Employees manualEntitiesParameterEntry() {
        int id;
        String name;
        long roomNumber;
        int salary;
        String position;

        while (true) {
            try {
                System.out.println("Введите id сотрудника");
                id = Integer.parseInt(reader.readLine());
                break;
            } catch (IOException | NumberFormatException e) {
                System.out.println("Некорректный ввод. Попробуйте ещё раз");
            }
        }
        while (true) {
            try {
                System.out.println("Введите имя сотрудника");
                name = reader.readLine();
                break;
            } catch (IOException e) {
                System.out.println("Неправильное имя. Введите еще раз");
            }
        }
        while (true) {
            try {
                System.out.println("Введите комнату сотрудника");
                roomNumber = Integer.parseInt(reader.readLine());
                break;
            } catch (IOException | NumberFormatException e) {
                System.out.println("Нужно ввести номер комнаты");
            }
        }
        while (true) {
            try {
                System.out.println("Введите зарплату сотрудника");
                salary = Integer.parseInt(reader.readLine());
                break;
            } catch (IOException | NumberFormatException e) {
                System.out.println("Введите пожалуйста число");
            }
        }
        while (true) {
            try {
                System.out.println("Введите должность сотрудника");
                position = reader.readLine();
                break;
            } catch (IOException e) {
                System.out.println("Некорректный ввод. Попробуйте еще раз");
            }
        }
        return new Employees(id, name, roomNumber, salary, position);
    }
}
