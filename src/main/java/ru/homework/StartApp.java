package ru.homework;

import ru.homework.consoleview.CommandHelper;
import ru.homework.dao.config.GlobalConfig;

import java.io.IOException;

public class StartApp {

    public static void main(String[] args) {
       try{
           GlobalConfig.initGlobalConfig();
       }catch (IOException e){
           e.printStackTrace();
       }
        new CommandHelper().startApp();
    }
}
