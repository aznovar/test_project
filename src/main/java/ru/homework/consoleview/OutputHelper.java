package ru.homework.consoleview;

import java.util.HashMap;
import java.util.List;

public interface OutputHelper<T> {

    /**
     * Выводит на консоль (додумать)
     * @param list
     */
    void outputAfterSingleSearch(List<T> list);

    /**
     * Вывод на консоль всей таблицы
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
