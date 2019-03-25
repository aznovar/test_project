package ru.homework;

import ru.homework.consoleview.CommandHelper;
import ru.homework.dao.config.GlobalConfigDatabase;

import java.io.IOException;

public class StartApp {

    public static void main(String[] args) {
        try {
            GlobalConfigDatabase.initGlobalConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new CommandHelper().startApp();
    }
}
