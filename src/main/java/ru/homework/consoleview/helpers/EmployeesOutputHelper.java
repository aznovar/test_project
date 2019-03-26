package ru.homework.consoleview.helpers;

import org.apache.log4j.Logger;
import ru.homework.dao.entity.Employees;

import java.util.HashMap;
import java.util.List;

/**
 * Класс имплементирует интерфейс OutputHelper и реализует методы для отображения
 * на консоли параметров сущности Employee пришедших после обработки таблицы бд\файла\etc определенным запросом
 */
public final class EmployeesOutputHelper implements OutputHelper<Employees> {

    private static final Logger log = Logger.getLogger(EmployeesOutputHelper.class);

    @Override
    public void outputAfterSingleSearch(List<Employees> list) {
        try {
            if (list == null || (list.get(0).getId() == 0) || (list.get(0).getName().isEmpty())) {
                System.out.println("\nНекорректный список\n");
            } else {
                System.out.println("\n" + list + "\n");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Такой комнаты нет в списке");
            log.info(e);
        }
    }

    @Override
    public void outputAll(List<Employees> list) {
        if (list.isEmpty()) {
            System.out.println("Нечего выводить");
        } else {
            System.out.println("Все записи из таблицы сотрудников");
            for (Employees employees : list) {
                System.out.println(employees.toString());
            }
            System.out.println("\n");
        }
    }

    @Override
    public void outputCount(Long number) {
        if (number == null) {
            System.out.println("в комнате пусто");
        } else {
            System.out.println("Число сотрудников в комнате: " + number.toString());
        }
    }

    @Override
    public void outputPairwise(List<HashMap<String, Object>> list) {
        if (list.isEmpty()) {
            System.out.println("Нечего выводить");
        } else {
            System.out.println("Все записи из таблицы сотрудников: \n" + list);

            System.out.println("\n");
        }
    }
}
