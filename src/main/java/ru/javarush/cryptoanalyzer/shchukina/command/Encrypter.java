package ru.javarush.cryptoanalyzer.shchukina.command;

import ru.javarush.cryptoanalyzer.shchukina.constant.Strings;
import ru.javarush.cryptoanalyzer.shchukina.entity.Result;
import ru.javarush.cryptoanalyzer.shchukina.entity.ResultCode;
import ru.javarush.cryptoanalyzer.shchukina.exception.AppException;
import ru.javarush.cryptoanalyzer.shchukina.util.PathFinder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Encrypter implements Action {

    @Override
    public Result execute(String[] parameters) {
        String source = parameters[0];
        String destination = parameters[1];
        Integer key = Integer.parseInt(parameters[2]);

        Path sourcePath = Path.of(PathFinder.getRoot() + source);
        Path destinationPath = Path.of(PathFinder.getRoot() + destination);

        try (FileWriter writer = new FileWriter(String.valueOf(destinationPath), false)) {
            List<String> strings = Files.readAllLines(sourcePath);
            List<String> encryptedStrings = new ArrayList<>();

            for (String string : strings) {
                encryptedStrings.add(rightShift(string, key));
                encryptedStrings.add("\n");
            }

            for (String string : encryptedStrings) {
                writer.write(string);
            }

            writer.flush();

        } catch (IOException e) {
            throw new AppException("Something went wrong! Please try again!\n" + e.getMessage());
        }

        return new Result(ResultCode.OK, "File " + sourcePath + " was encrypted successfully!");
    }

    private String rightShift(String string, Integer key) {
        String encryptedString;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            char symbol = string.charAt(i);
            int alphabetSymbolIndex = Strings.ALPHABET.indexOf(symbol);

            if (alphabetSymbolIndex != -1) {
                builder.append(Strings.ALPHABET.charAt((alphabetSymbolIndex + key) % Strings.ALPHABET.length()));
            } else {
                builder.append(symbol);
            }
        }
        encryptedString = builder.toString();

        return encryptedString;
    }
}

