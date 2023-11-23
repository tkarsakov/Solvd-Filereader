package com.solvd.textops;

import com.solvd.file.FileHandler;
import com.solvd.ui.ImpossibleQueryException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextManager {
    private static final Logger LOGGER = LogManager.getLogger(TextManager.class);
    private String data;

    public TextManager(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void countUniqueWords() {
        // Use regex to remove all non-character symbols
        String strippedData = data.toLowerCase().replaceAll("[^a-z ]", "");
        ArrayList<String> strings = new ArrayList<>(List.of(StringUtils.split(strippedData)));
        ArrayList<String> outputList = new ArrayList<>();
        for (String s : strings) {
            if (!outputList.contains(s)) {
                outputList.add(s);
                // Create a regex pattern that represents a singular word surrounded by whitespace
                String regexp = "\\s*\\b" + s + "\\b\\s*";
                strippedData = strippedData.replaceAll(regexp, " ");
            }
        }
        StringBuilder output = new StringBuilder(StringUtils.center("UNIQUE WORDS", 24, "_"));
        output.append("\n");
        output.append("Base text: ").append(data).append("\n");
        output.append("Number of unique words: ").append(outputList.size()).append("\n");
        LOGGER.info("Number of unique words: " + outputList.size());
        if (FileHandler.stringToFile(output.toString())) {
            LOGGER.info("Unique words saved in " + FileHandler.getOutput());
        } else {
            LOGGER.info("Saving failed due to IO exception");
        }
    }

    public void countLetters() {
        // Use regex to remove all non-character symbols
        String strippedData = data.toUpperCase().replaceAll("[^A-Z]", "");
        char[] chars = strippedData.toCharArray();
        StringBuilder output = new StringBuilder(StringUtils.center("NUMBER OF CHARACTERS", 32, "_"));
        output.append("\n");
        output.append("Base text: ").append(data).append("\n");
        output.append("Result: ").append(StringUtils.join(chars, ' ')).append("\n");
        output.append("Number of characters: ").append(chars.length).append("\n");
        LOGGER.info("Number of characters: " + chars.length);
        if (FileHandler.stringToFile(output.toString())) {
            LOGGER.info("Unique words saved in " + FileHandler.getOutput());
        } else {
            LOGGER.info("Saving failed due to IO exception");
        }
    }

    public void searchWord(String query) {
        if (query.length() <= 2) {
            throw new ImpossibleQueryException("Query contains 2 or less letters.");
        } else if (!StringUtils.isAlpha(query)) {
            throw new ImpossibleQueryException("Query contains symbols other than Unicode letters");
        }
        String queryLower = query.toLowerCase();
        // Create a regex pattern that represents a singular word surrounded by whitespace
        Pattern pattern = Pattern.compile("\\s*\\b" + queryLower + "\\b\\s*");
        // Use regex to remove all non-character symbols
        Matcher matcher = pattern.matcher(data.toLowerCase().replaceAll("[^a-z ]", ""));
        int matchesFound = 0;
        // Using matcher.find() in a while loop allows it to work through the entire string
        // only increasing the matchesFound counter if there are matches left
        while (matcher.find()) {
            matchesFound++;
        }
        StringBuilder output = new StringBuilder(StringUtils.center("SEARCH WORD", 23, "_"));
        output.append("\n");
        output.append("Base text: ").append(data).append("\n");
        output.append("Searching for: ").append(query).append("\n");
        output.append("Result: ").append("Found ").append(matchesFound).append(" matches").append("\n");
        LOGGER.info("Number of matches: " + matchesFound);
        if (FileHandler.stringToFile(output.toString())) {
            LOGGER.info("Search results saved in " + FileHandler.getOutput());
        } else {
            LOGGER.info("Saving failed due to IO exception");
        }
    }
}
