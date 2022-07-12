package ru.javarush.cryptoanalyzer.shchukina.controller;

import ru.javarush.cryptoanalyzer.shchukina.command.Action;
import ru.javarush.cryptoanalyzer.shchukina.entity.Result;
import ru.javarush.cryptoanalyzer.shchukina.entity.ResultCode;
import ru.javarush.cryptoanalyzer.shchukina.exception.AppException;

public class MainController {

    public Result execute(String command, String[] parameters) {

        try {
            Action action = Actions.find(command);
            return action.execute(parameters);
        } catch (AppException e) {
            return new Result(ResultCode.ERROR, e.getMessage());
        }
    }
}
