package ru.javarush.cryptoanalyzer.shchukina.controller;

import ru.javarush.cryptoanalyzer.shchukina.command.Action;
import ru.javarush.cryptoanalyzer.shchukina.entity.Result;
import ru.javarush.cryptoanalyzer.shchukina.entity.ResultCode;
import ru.javarush.cryptoanalyzer.shchukina.exception.AppException;

public class MainController {

    public Result execute(String command, String[] parameters) {

        try {
            Action action = Actions.find(command);
            Result result = action.execute(parameters);
            System.out.println(result);
            return result;

        } catch (AppException e) {
            System.out.println(e.getMessage());
            return new Result(ResultCode.ERROR, e.getMessage());
        }
    }
}
