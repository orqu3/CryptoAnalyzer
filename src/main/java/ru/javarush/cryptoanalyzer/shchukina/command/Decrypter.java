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
import java.util.List;

import static ru.javarush.cryptoanalyzer.shchukina.constant.Strings.ALPHABET;

public class Decrypter implements Action {

    @Override
    public Result execute(String[] parameters) {
        String source = parameters[0];
        String destination = parameters[1];
        Integer key = Integer.parseInt(parameters[2]);

        Path sourcePath = Path.of(PathFinder.getRoot() + source);
        Path destinationPath = Path.of(PathFinder.getRoot() + destination);

        try (FileWriter writer = new FileWriter(String.valueOf(destinationPath), false)) {
            List<String> encryptedStrings = Files.readAllLines(sourcePath);
            List<String> decryptedStrings = new ArrayList<>();

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
        String decryptedString;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            char symbol = string.toLowerCase().charAt(i);
            int alphabetSymbolIndex = ALPHABET.indexOf(symbol);
            int offset = (alphabetSymbolIndex + (ALPHABET.length() - (key % ALPHABET.length()))) % ALPHABET.length();


            if (alphabetSymbolIndex != -1) {
                builder.append(ALPHABET.charAt(offset));
            } else {
                builder.append(symbol);
            }
        }
        decryptedString = builder.toString();

        return decryptedString;
    }
}
