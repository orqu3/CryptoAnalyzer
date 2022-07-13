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
                case 0 -> printInstructions();
                case 1 -> result = encrypt();
                case 2 -> result = decrypt();
                case 3 -> result = bruteForceHack();
                case 4 -> result = statisticAnalyze();
                case 5 -> quit = true;
                default -> System.out.println("You entered incorrect value. Please, try again.");
            }
        }

        return result;
    }

    private void printInstructions() {
        System.out.println("Choose action, enter: ");
        System.out.println("\t 0 - To print instructions.");
        System.out.println("\t 1 - To encrypt data.");
        System.out.println("\t 2 - To decrypt data (have a key).");
        System.out.println("\t 3 - To decrypt data by brute force way (no key).");
        System.out.println("\t 4 - To decrypt by statistical analysis.");
        System.out.println("\t 5 - To exit.");
    }

    private Result encrypt() {
        System.out.println("Enter: source, destination for encrypted file and key");
        String fileName = scanner.nextLine();
        String encryptedFileName = scanner.nextLine();
        String key = scanner.nextLine();
        String[] parameters = new String[3];
        parameters[0] = fileName;
        parameters[1] = encryptedFileName;
        parameters[2] = key;

        return mainController.execute("encrypt", parameters);
    }

    private Result decrypt() {
        System.out.println("Enter: encrypted source, destination for decrypted file and key");
        String encryptedFileName = scanner.nextLine();
        String decryptedFileName = scanner.nextLine();
        String key = scanner.nextLine();
        String[] parameters = new String[3];
        parameters[0] = encryptedFileName;
        parameters[1] = decryptedFileName;
        parameters[2] = key;

        return mainController.execute("decrypt", parameters);
    }

    private Result bruteForceHack() {
        System.out.println("Enter: encrypted source and destination for decrypted file");
        String encryptedSource = scanner.nextLine();
        String destination = scanner.nextLine();
        String[] parameters = new String[2];
        parameters[0] = encryptedSource;
        parameters[1] = destination;

        return mainController.execute("decrypt_by_brute_force", parameters);
    }

    private Result statisticAnalyze() {
        System.out.println("Enter: encrypted source, dictionary and destination for decrypted file");
        String encryptedSource = scanner.nextLine();
        String dictionary = scanner.nextLine();
        String destination = scanner.nextLine();
        String[] parameters = new String[3];
        parameters[0] = encryptedSource;
        parameters[1] = dictionary;
        parameters[2] = destination;

        return mainController.execute("decrypt_by_statistical_analyze", parameters);
    }
}
