package ru.javarush.cryptoanalyzer.shchukina.constant;

import static ru.javarush.cryptoanalyzer.shchukina.constant.Colours.*;

public class Messages {

    public static final String INSTRUCTIONS = PURPLE_BOLD + "Choose action, enter:\n" + ANSI_RESET +
            "\t 0 - To print instructions.\n" +
            "\t 1 - To encrypt data.\n" +
            "\t 2 - To decrypt data (have a key).\n" +
            "\t 3 - To decrypt data by brute force way (no key).\n" +
            "\t 4 - To decrypt by statistical analysis.\n" +
            "\t 5 - To exit.\n";

    public static final String INCORRECT_VALUE_MESSAGE = ANSI_RED + "You entered incorrect value. Please, try again!" + ANSI_RESET;

    public static final String ENCRYPT_MESSAGE = ANSI_PURPLE + "Enter: " + ANSI_RESET + "source, destination for " +
            "encrypted file and key.\n" + ANSI_PURPLE + "OR copy this: " + ANSI_RESET + ANSI_CYAN +
            "text.txt encrypted.txt 6" + ANSI_RESET;

    public static final String DECRYPT_MESSAGE = ANSI_PURPLE + "Enter: " + ANSI_RESET + "source, destination for " +
            "decrypted file and key.\n" + ANSI_PURPLE + "OR copy this: " + ANSI_RESET + ANSI_CYAN +
            "encrypted.txt decrypted.txt 6" + ANSI_RESET;

    public static final String BRUTE_FORCE_MESSAGE = ANSI_PURPLE + "Enter: " + ANSI_RESET + "encrypted source and destination for " +
            "decrypted file.\n" + ANSI_PURPLE + "OR copy this: " + ANSI_RESET + ANSI_CYAN +
            "encrypted.txt bruteforced.txt" + ANSI_RESET;

    public static final String STATISTIC_ANALYZE_MESSAGE = ANSI_PURPLE + "Enter: " + ANSI_RESET + "encrypted source, dictionary and " +
            "destination for decrypted file.\n" + ANSI_PURPLE + "OR copy this: " + ANSI_RESET + ANSI_CYAN +
            "encrypted.txt dictionary.txt analyzed.txt" + ANSI_RESET;

}
