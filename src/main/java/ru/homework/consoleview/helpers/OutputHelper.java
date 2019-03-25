package ru.homework.consoleview.helpers;

import java.util.HashMap;
import java.util.List;

/**
 * Используя этот интерфейс можно осуществить вывод на консоль пришедших на вход параметров
 * @param <T>
 */
public interface OutputHelper<T> {


    /**
     * Выводит на консоль результат поиска, как правило используется для вывода строки из таблицы
     * строка будет являться частью списка
     * @param list
     */
    void outputAfterSingleSearch(List<T> list);

    /**
     * Вывод на консоль всей таблицы в виде списка
     * @param list
     */
    void outputAll(List<T> list);


    /**
     * Вывод на консоль определенного числа сущности по запросу
     * @param number
     */
    void outputCount(Long number);

    /**
     * Вывод на консоль списка связанных параметров
     * @param list
     */
    void outputPairwise(List<HashMap<String,Object>> list);



}
