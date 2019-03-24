package ru.homework.consoleview;

import ru.homework.dao.entity.Employees;

import java.util.HashMap;
import java.util.List;

public final class EmployeesOutputHelper implements OutputHelper<Employees> {


    @Override
    public void outputAfterSingleSearch(List<Employees> list) {
        if (list == null || (list.get(0).getId() == 0)) {
            CommandHelper.writeToConsole("\nСотрудник не найден\n");
        } else {
            CommandHelper.writeToConsole("\n" + list + "\n");
        }
    }

    @Override
    public void outputAll(List<Employees> list) {
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

    @Override
    public void outputCount(Long number) {
        if (number == null) {
            CommandHelper.writeToConsole("в комнате пусто");
        } else {
            CommandHelper.writeToConsole("Число сотрудников в комнате: " + number.toString());
        }
    }

    @Override
    public void outputPairwise(List<HashMap<String,Object>> list) {
        if (list.isEmpty()) {
            CommandHelper.writeToConsole("Нечего выводить");
        } else {
            CommandHelper.writeToConsole("Все записи из таблицы сотрудников: \n" + list);

            CommandHelper.writeToConsole("\n");
        }
    }
}
