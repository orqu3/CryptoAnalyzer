package ru.javarush.cryptoanalyzer.shchukina.controller;

import ru.javarush.cryptoanalyzer.shchukina.command.*;

public enum Actions {
    ENCRYPT(new Encrypter()),
    DECRYPT(new Decripter()),
    HACK_WITH_BRUTE_FORCE(new BruteForcer()),
    HACK_WITH_STATIC_ANALYZE(new StaticAnalyzer());

    private final Action action;


    Actions(Action action) {
        this.action = action;
    }

    public static Action find(String command) {
        return Actions.valueOf(command.toUpperCase()).action;
    }
}
