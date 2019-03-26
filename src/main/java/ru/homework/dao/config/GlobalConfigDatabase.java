package ru.homework.dao.config;

import ru.homework.StartApp;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
     * если null или пустое - берем файл по умолчанию.
     *
     * @param name
     * @throws IOException
     */
    public static void initGlobalConfig(InputStream name) throws IOException {
        InputStream is = StartApp.class.getClassLoader().getResourceAsStream("database.properties");
        if (name != null) {
            GLOBAL_CONFIG.load(new InputStreamReader(name));
        } else {
            GLOBAL_CONFIG.load(new InputStreamReader(is));
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
