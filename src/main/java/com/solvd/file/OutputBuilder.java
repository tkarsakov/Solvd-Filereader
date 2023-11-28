package com.solvd.file;

import com.solvd.textops.Operation;
import org.apache.commons.lang3.StringUtils;

public class OutputBuilder {
    private final StringBuilder outputBuilder = new StringBuilder();
    public boolean newTextEntered = true;
    public void addToOutput(Operation operation, String data, int result, String... context){
        if(newTextEntered) {
            outputBuilder.append("\n#######################\n");
            outputBuilder.append("Base text: ").append(data).append("\n");
            newTextEntered = false;
        }
        switch(operation) {
            case COUNT_UNIQUE_WORDS:
                outputBuilder.append(StringUtils.center("UNIQUE WORDS", 24, "_")).append("\n");
                outputBuilder.append("Number of unique words: ").append(result).append("\n");
                break;
            case COUNT_LETTERS:
                outputBuilder.append(StringUtils.center("NUMBER OF CHARACTERS", 32, "_")).append("\n");
                outputBuilder.append("Result: ").append(context[0]).append("\n");
                outputBuilder.append("Number of characters: ").append(result).append("\n");
                break;
            case SEARCH_WORD:
                outputBuilder.append(StringUtils.center("SEARCH WORD", 23, "_")).append("\n");
                outputBuilder.append("Searching for: ").append(context[0]).append("\n");
                outputBuilder.append("Result: ").append("Found ").append(result).append(" matches").append("\n");
                break;
        }
    }
    public String buildOutput() {
        return outputBuilder.toString();
    }
}
