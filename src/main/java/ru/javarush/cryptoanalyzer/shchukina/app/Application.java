package ru.javarush.cryptoanalyzer.shchukina.app;

import ru.javarush.cryptoanalyzer.shchukina.controller.MainController;
import ru.javarush.cryptoanalyzer.shchukina.entity.Result;

import java.util.Scanner;

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
                case 1 -> result = encrypt();
                case 2 -> result = decrypt();
                case 3 -> result = bruteForceHack();
                case 4 -> result = staticAnalyze();
                case 5 -> quit = true;
                default -> System.out.println("Вы ввели некорректное значение! Попробуйте еще раз.");
            }
        }

        return result;
    }

    private void printInstructions() {
        System.out.println("Выберите действие, введите в консоль: ");
        System.out.println("\t 1 - Чтобы зашифровать данные.");
        System.out.println("\t 2 - Чтобы расшифровать данные (при наличии ключа).");
        System.out.println("\t 3 - Чтобы расшифровать данные методом brute force (ключ неизвестен).");
        System.out.println("\t 4 - Чтобы провести статистический анализ.");
        System.out.println("\t 5 - Чтобы выйти из приложения.");
    }

    private Result encrypt() {
        System.out.println("Введите имя исходного файла, зашифрованного файла и ключ");
        String fileName = scanner.nextLine();
        String encryptedFileName = scanner.nextLine();
        String key = scanner.nextLine();
        String [] parameters = new String[3];
        parameters[0] = fileName;
        parameters[1] = encryptedFileName;
        parameters[2] = key;

        return mainController.execute("encrypt", parameters);
    }

    private Result decrypt() {
        System.out.println("Введите имя файла, который нужно расшифровать, имя расшифрованного файла и ключ");
        String encryptedFileName = scanner.nextLine();
        String decryptedFileName = scanner.nextLine();
        String key = scanner.nextLine();
        String [] parameters = new String[3];
        parameters[0] = encryptedFileName;
        parameters[1] = decryptedFileName;
        parameters[2] = key;

        return mainController.execute("decrypt", parameters);
    }

    private Result bruteForceHack() {
        return null;
    }

    private Result staticAnalyze() {
        return null;
    }
}
