package ru.homework.dao.connection.fileconnection;

/**
 * Данный класс служит для инстанса объектов реализаций интерфейса FileConnectionBuilder
 */
public final class ConnectionToFileBuilderFactory {

    private ConnectionToFileBuilderFactory() {

    }

    /**
     * Статический метод для создания объекта реализации интерфейса FileConnectionBuilder
     * для доступа к таблице типа CSV
     *
     * @return
     */
    public static FileConnectionBuilder getCsvFile() {
        return new CsvConnectionBuilder();
    }
}
