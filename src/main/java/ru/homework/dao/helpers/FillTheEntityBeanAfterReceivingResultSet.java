package ru.homework.dao.helpers;

import java.sql.ResultSet;

/**
 * Для того чтобы выводить(показывать клиенту) результаты запроса к БД
 * нужно заполнить поля объекта(entity конкретной таблицы, например)
 * множеством полученных значений типа ResultSet
 *
 * @param <T>
 */
public interface FillTheEntityBeanAfterReceivingResultSet<T> {

    /**
     * Позволяет заполнить сущность результатом запроса к БД
     *
     * @param resultSet
     * @return
     */
    T fiilTheEntityBeanByResultSet(ResultSet resultSet);

}
