package ru.homework;

import ru.homework.consoleview.ConsoleComand;
import ru.homework.dao.config.GlobalConfigDatabase;

import java.io.IOException;

public class StartApp {


    public static void main(String[] args) {
        try {
            GlobalConfigDatabase.initGlobalConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new ConsoleComand().startApp();
    }
}
