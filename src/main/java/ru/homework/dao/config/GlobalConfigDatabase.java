package ru.homework.dao.config;

import ru.homework.dao.params.DatabaseConfigEnum;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public final class GlobalConfigDatabase {

    private GlobalConfigDatabase() {

    }

    private static final Properties GLOBAL_CONFIG = new Properties();

    /**
     * Начальная загрузка параметров из файла по умолчанию
     *
     * @throws IOException
     */
    public static void initGlobalConfig() throws IOException {
        initGlobalConfig(null);
    }


    /**
     * Позволяет загрузку данных из конфигурационного файла,
     * имя которого передано в виде параметра
     * если null ли пустое - берем файл по умолчанию.
     *
     * @param name
     * @throws IOException
     */
    public static void initGlobalConfig(String name) throws IOException {
        if (name != null && !name.trim().isEmpty()) {
            GLOBAL_CONFIG.load(new FileReader(name));
        } else {
            GLOBAL_CONFIG.load(new FileReader(DatabaseConfigEnum.CONFIG_NAME.getContProp()));
        }
    }

    /**
     * Получаем значение параметра из глобальной конфигурации по имени
     *
     * @param property
     * @return
     */
    public static String getProperty(String property) {
        return GLOBAL_CONFIG.getProperty(property);
    }
}
