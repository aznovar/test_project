package ru.homework.dao.helpers;

import java.sql.ResultSet;

public interface FillTheEntityBeanAfterReceivingResultSet<T> {

    /**
     * Позволяет заполнить сущность результатом запроса к БД
     * @param resultSet
     * @return
     */
    T fiilTheEntityBeanByResultSet(ResultSet resultSet);

}
