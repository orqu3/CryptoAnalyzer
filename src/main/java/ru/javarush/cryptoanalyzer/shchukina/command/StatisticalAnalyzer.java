package ru.javarush.cryptoanalyzer.shchukina.command;

import ru.javarush.cryptoanalyzer.shchukina.entity.Result;
import ru.javarush.cryptoanalyzer.shchukina.entity.ResultCode;
import ru.javarush.cryptoanalyzer.shchukina.exception.AppException;
import ru.javarush.cryptoanalyzer.shchukina.util.PathFinder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.javarush.cryptoanalyzer.shchukina.constant.Strings.ALPHABET;

public class StatisticalAnalyzer implements Action {

    @Override
    public Result execute(String[] parameters) {

        Path sourcePath = Path.of(PathFinder.getRoot() + parameters[0]);
        Path dictionaryPath = Path.of(PathFinder.getRoot() + parameters[1]);
        Path destinationPath = Path.of(PathFinder.getRoot() + parameters[2]);

        try (FileWriter writer = new FileWriter(String.valueOf(destinationPath), false)) {
            List<String> encryptedStrings = Files.readAllLines(sourcePath);
            List<String> dictionaryStrings = Files.readAllLines(dictionaryPath);

            Map<Character, Double> encryptedFrequencyMap = getFrequencyDictionary(encryptedStrings);
            Map<Character, Double> dictionaryFrequencyMap = getFrequencyDictionary(dictionaryStrings);

            List<String> decryptedStrings = new ArrayList<>();

            int key = findKey(encryptedFrequencyMap, dictionaryFrequencyMap);

            for (String string : encryptedStrings) {
                decryptedStrings.add(leftShift(string, key));
                decryptedStrings.add("\n");
            }

            for (String string : decryptedStrings) {
                writer.write(string);
            }

            writer.flush();

        } catch (IOException e) {
            throw new AppException("Something went wrong! Please try again!\n" + e.getMessage());
        }

        return new Result(ResultCode.OK, "File " + sourcePath + " was decrypted successfully!");
    }

    private String leftShift(String string, Integer key) {
        StringBuilder decrypted = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            char symbol = string.toLowerCase().charAt(i);
            int alphabetSymbolIndex = ALPHABET.indexOf(symbol);
            int offset = (alphabetSymbolIndex + (ALPHABET.length() - (key % ALPHABET.length()))) % ALPHABET.length();

            if (alphabetSymbolIndex != -1) {
                decrypted.append(ALPHABET.charAt(offset));
            } else {
                decrypted.append(symbol);
            }
        }
        return decrypted.toString();
    }

    private int findKey(Map<Character, Double> encryptedFrequencyMap,
                        Map<Character, Double> dictionaryFrequencyMap) {

        char encryptedChar = findMostCommonSymbol(encryptedFrequencyMap);
        char dictionaryChar = findMostCommonSymbol(dictionaryFrequencyMap);

        int indexOfEncryptedChar = ALPHABET.indexOf(encryptedChar);
        int indexOfDictionaryChar = ALPHABET.indexOf(dictionaryChar);

       return indexOfEncryptedChar - indexOfDictionaryChar;
    }

    private char findMostCommonSymbol(Map<Character, Double> frequencyMap) {
        char mostCommonSymbol = 0;
        double maxValue = 0;

        for (Map.Entry<Character, Double> item : frequencyMap.entrySet()) {
            if (item.getValue() > maxValue) {
                maxValue = item.getValue();
                mostCommonSymbol = item.getKey();
            }
        }
        return mostCommonSymbol;
    }

    private Map<Character, Double> getFrequencyDictionary(List<String> list) {
        Map<Character, Double> symbolsAndCount = new HashMap<>();

        for (String string : list) {
            char[] chars = string.toLowerCase().toCharArray();

            for (char symbol : chars) {
                if (!symbolsAndCount.containsKey(symbol)) {
                    symbolsAndCount.put(symbol, 1.0);
                } else {
                    symbolsAndCount.put(symbol, symbolsAndCount.get(symbol) + 1.0);
                }
            }
        }
        int sum = 0;
        for (String string : list) {
            sum += string.length();
        }

        for (Map.Entry<Character, Double> characters : symbolsAndCount.entrySet()) {
            double value = characters.getValue();
            double percent = value / sum * 100;
            symbolsAndCount.put(characters.getKey(), percent);
        }
        return symbolsAndCount;
    }
}


