package ru.javarush.cryptoanalyzer.shchukina.app;

import ru.javarush.cryptoanalyzer.shchukina.controller.MainController;
import ru.javarush.cryptoanalyzer.shchukina.entity.Result;

import java.util.Scanner;

import static ru.javarush.cryptoanalyzer.shchukina.constant.Messages.*;


public class Application {

    private final MainController mainController;

    Scanner scanner = new Scanner(System.in);

    public Application(MainController mainController) {
        this.mainController = mainController;
    }

    public Result run() {

        boolean quit = false;
        printInstructions();
        Result result = null;

        while (!quit) {
            int choice = scanner.nextInt();

            switch (choice) {
                case 0 -> {
                    result = null;
                    printInstructions();
                }
                case 1 -> result = encrypt();
                case 2 -> result = decrypt();
                case 3 -> result = bruteForceHack();
                case 4 -> result = statisticAnalyze();
                case 5 -> {
                    result = null;
                    quit = true;
                }
                default -> {
                    result = null;
                    System.out.println(INCORRECT_VALUE_MESSAGE);
                }
            }
        }
        return result;
    }

    private void printInstructions() {
        System.out.println(INSTRUCTIONS);
    }

    private Result encrypt() {
        System.out.println(ENCRYPT_MESSAGE);
        String[] parameters = getParameters(3);

        return mainController.execute("ENCRYPT", parameters);
    }

    private Result decrypt() {
        System.out.println(DECRYPT_MESSAGE);
        String[] parameters = getParameters(3);

        return mainController.execute("DECRYPT", parameters);
    }

    private Result bruteForceHack() {
        System.out.println(BRUTE_FORCE_MESSAGE);
        String[] parameters = getParameters(2);

        return mainController.execute("DECRYPT_BY_BRUTE_FORCE", parameters);
    }

    private Result statisticAnalyze() {
        System.out.println(STATISTIC_ANALYZE_MESSAGE);
        String[] parameters = getParameters(3);
        return mainController.execute("DECRYPT_BY_STATISTICAL_ANALYZE", parameters);
    }

    private String[] getParameters(int countOfParameters) {
        String[] parameters = new String[countOfParameters];
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = scanner.next();
        }
        return parameters;
    }
}
