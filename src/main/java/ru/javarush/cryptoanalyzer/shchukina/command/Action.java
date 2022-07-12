package ru.javarush.cryptoanalyzer.shchukina.command;

import ru.javarush.cryptoanalyzer.shchukina.entity.Result;

public interface Action {

    Result execute(String[] parameters);

}
