package ru.homework.service;

public interface SourceDistributor<T> {
    /**
     * Метод для выбора источника данных. Реализуется специальным классом
     * в котором указывается сущность DAO для которой будет выбран источник данных
     * @param source
     * @return
     * @throws NullPointerException
     */
    T chooseTheSource(int source);

}
