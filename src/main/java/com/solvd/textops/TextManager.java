package com.solvd.textops;

import com.solvd.file.FileHandler;
import com.solvd.file.OutputBuilder;
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
    private final OutputBuilder outputBuilder;

    public TextManager(String data) {
        this.data = data;
        this.outputBuilder = new OutputBuilder();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        outputBuilder.newTextEntered = true;
    }
    public String buildOutput (){
        return outputBuilder.buildOutput();
    }
    public void countUniqueWords() {
        // Use regex to remove all non-character symbols
        String strippedData = data.toLowerCase().replaceAll("[^а-яa-z ]", "");
        ArrayList<String> outputList = new ArrayList<>();
        for (String s : StringUtils.split(strippedData)) {
            if (!outputList.contains(s)) {
                outputList.add(s);
            }
        }
        outputBuilder.addToOutput(Operation.COUNT_UNIQUE_WORDS, data, outputList.size());
        LOGGER.info("Number of unique words: " + outputList.size());
    }

    public void countLetters() {
        // Use regex to remove all non-character symbols
        String strippedData = data.toUpperCase().replaceAll("[^А-ЯA-Z]", "");
        char[] chars = strippedData.toCharArray();
        outputBuilder.addToOutput(Operation.COUNT_LETTERS, data, chars.length, StringUtils.join(chars, ' '));
        LOGGER.info("Number of characters: " + chars.length);
    }

    public void searchWord(String query) {
        if (query.length() <= 2) {
            throw new ImpossibleQueryException("Query contains 2 or less letters.");
        } else if (!StringUtils.isAlpha(query)) {
            throw new ImpossibleQueryException("Query contains symbols other than Unicode letters");
        }
        String queryLower = query.toLowerCase();
        String strippedData = data.toLowerCase().replaceAll("[^а-яa-z ]", "");
        int matchesFound = 0;
        for (String word: StringUtils.split(strippedData)){
            if (queryLower.equals(word)){
                matchesFound++;
            }
        }
        outputBuilder.addToOutput(Operation.SEARCH_WORD, data, matchesFound, query);
        LOGGER.info("Number of matches: " + matchesFound);
    }

}
