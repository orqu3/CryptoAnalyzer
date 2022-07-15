package ru.javarush.cryptoanalyzer.shchukina.command;

import ru.javarush.cryptoanalyzer.shchukina.entity.Result;
import ru.javarush.cryptoanalyzer.shchukina.entity.ResultCode;
import ru.javarush.cryptoanalyzer.shchukina.exception.AppException;
import ru.javarush.cryptoanalyzer.shchukina.util.PathFinder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class StatisticalAnalyzer implements Action {

    @Override
    public Result execute(String[] parameters) {
        String source = parameters[0];
        String dictionary = parameters[1];
        String destination = parameters[2];

        Path sourcePath = Path.of(PathFinder.getRoot() + source);
        Path dictionaryPath = Path.of(PathFinder.getRoot() + dictionary);
        Path destinationPath = Path.of(PathFinder.getRoot() + destination);

        try (FileWriter writer = new FileWriter(String.valueOf(destinationPath), false)) {
            List<String> encryptedStrings = Files.readAllLines(sourcePath);
            List<String> dictionaryStrings = Files.readAllLines(dictionaryPath);

            Map<Character, Integer> frequencyDictionaryForEncrypted = getFrequencyDictionary(encryptedStrings);
            Map<Character, Integer> frequencyDictionaryForDictionary = getFrequencyDictionary(dictionaryStrings);

            System.out.println(frequencyDictionaryForDictionary);
            System.out.println(frequencyDictionaryForEncrypted);






            List<String> decryptedStrings = new ArrayList<>();


            writer.flush();

        } catch (IOException e) {
            throw new AppException("Something went wrong! Please try again!\n" + e.getMessage());
        }

        return new Result(ResultCode.OK, "File " + sourcePath + " was decrypted successfully!");
    }

    public static Map<Character, Integer> getFrequencyDictionary(List<String> list) {
        Map<Character, Integer> symbolsAndCount = new HashMap<>();

        for (String string : list) {
            char[] chars = string.toCharArray();

            for (char symbol : chars) {
                if (!symbolsAndCount.containsKey(symbol)) {
                    symbolsAndCount.put(symbol, 1);
                } else {
                    symbolsAndCount.put(symbol, symbolsAndCount.get(symbol) + 1);
                }
            }
        }
        return symbolsAndCount;
    }

}

