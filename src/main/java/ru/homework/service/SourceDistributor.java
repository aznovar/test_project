package ru.homework.service;

public interface SourceDistributor<T> {
    /**
     * Метод для выбора источника данных
     * @param source
     * @return
     * @throws NullPointerException
     */
    T chooseTheSource(int source) throws NullPointerException;

}
