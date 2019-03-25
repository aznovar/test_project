package ru.homework.dao.helpers;

import java.util.List;

/**
 * При работе с источником данных представленном в виде файла
 * значения, которые будут получены после парсинга файла, должны быть представлены в виде списка
 * @param <T> - в качестве типа может выступать любая entity
 */
public interface ContentParser<T> {

    /**
     * Метод реализующий сбор контента из файла
     * @return
     */
    List<T> getContentOfFile();
}
