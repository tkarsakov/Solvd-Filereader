package com.solvd.textops;

import com.solvd.file.FileHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

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

    public void countAndWriteUniqueWords() {
        String strippedData = data.toLowerCase().replaceAll("[^a-z ]", "");
        ArrayList<String> strings = new ArrayList<>(List.of(StringUtils.split(strippedData)));
        ArrayList<String> outputList = new ArrayList<>();
        for (String s : strings) {
            if (!outputList.contains(s)) {
                outputList.add(s);
                String regexp = "\\s*\\b" + s + "\\b\\s*";
                strippedData = strippedData.replaceAll(regexp, " ");
            }
        }
        LOGGER.info("Number of unique words: " + outputList.size());
        if (FileHandler.stringToFile(StringUtils.join(outputList, " "), "unique_words.txt")) {
            LOGGER.info("Unique words saved in unique_words.txt");
        } else {
            LOGGER.info("Saving failed due to IO exception");
        }
    }

    public void outputAndCountLetters() {
        String strippedData = data.toUpperCase().replaceAll("[^A-Z]", "");
        char[] chars = strippedData.toCharArray();
        LOGGER.info("Number of characters: " + chars.length);
        if (FileHandler.stringToFile(StringUtils.join(chars, ' '), "characters.txt")) {
            LOGGER.info("Unique words saved in characters.txt");
        } else {
            LOGGER.info("Saving failed due to IO exception");
        }
    }
}
