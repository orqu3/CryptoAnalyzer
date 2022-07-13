package ru.javarush.cryptoanalyzer.shchukina;

import ru.javarush.cryptoanalyzer.shchukina.app.Application;
import ru.javarush.cryptoanalyzer.shchukina.controller.MainController;
import ru.javarush.cryptoanalyzer.shchukina.entity.Result;

public class AppLauncher {
    public static void main(String[] args) {
        MainController mainController = new MainController();

        Application application = new Application(mainController);
        Result result = application.run();

        if (result != null) {
            System.out.println(result);
        }
    }
}
