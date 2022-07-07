package ru.javarush.cryptoanalyzer.shchukina.app;

import ru.javarush.cryptoanalyzer.shchukina.controller.MainController;
import ru.javarush.cryptoanalyzer.shchukina.service.DecriptService;
import ru.javarush.cryptoanalyzer.shchukina.service.EncryptService;

import java.util.Scanner;

public class Application {

    Scanner scanner = new Scanner(System.in);

    private final DecriptService decriptService = new DecriptService();
    private final EncryptService encryptService = new EncryptService();
    private final MainController mainController = new MainController(decriptService, encryptService);

    public Application() {
    }

    public void run() {
        boolean quit = false;

        printInstructions();

        while (!quit) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 0 -> printInstructions();
                case 1 -> mainController.encrypt();
                case 2 -> mainController.decrypt();
                //case 3 -> doSmthng();
                //case 4 -> doSmthng();
                case 5 -> quit = true;
                default -> System.out.println("Вы ввели некорректное значение! Попробуйте еще раз.");
            }
        }
    }

    private void printInstructions() {
        System.out.println("Привет. Я твой личный криптоанализатор!");
        System.out.println("Введи в консоль: ");
        System.out.println("\t 0 - Чтобы прочитать инструкции.");
        System.out.println("\t 1 - Если тебе нужно зашифровать данные.");
        System.out.println("\t 2 - Если тебе нужно расшифровать данные.");
        System.out.println("\t 3 - ");
        System.out.println("\t 4 - ");
        System.out.println("\t 5 - Чтобы выйти из приложения.");
    }
}
