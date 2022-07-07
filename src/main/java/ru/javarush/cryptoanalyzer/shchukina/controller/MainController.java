package ru.javarush.cryptoanalyzer.shchukina.controller;

import ru.javarush.cryptoanalyzer.shchukina.service.DecriptService;
import ru.javarush.cryptoanalyzer.shchukina.service.EncryptService;

public class MainController {

    private final DecriptService decriptService;
    private final EncryptService encryptService;

    public MainController(DecriptService decriptService, EncryptService encryptService) {
        this.decriptService = decriptService;
        this.encryptService = encryptService;
    }

    public void encrypt() {
        encryptService.encrypt();
    }

    public void decrypt() {
        decriptService.decrypt();
    }

}
