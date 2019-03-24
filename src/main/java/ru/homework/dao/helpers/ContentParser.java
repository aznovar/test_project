package ru.homework.dao.helpers;

import java.util.List;

public interface ContentParser<T> {

    List<T> getContentOfFile();
}
