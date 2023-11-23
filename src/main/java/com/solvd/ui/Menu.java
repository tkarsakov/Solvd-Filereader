package com.solvd.ui;

import com.solvd.file.FileHandler;
import com.solvd.textops.TextManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Menu {
    private static final Logger LOGGER = LogManager.getLogger(Menu.class);
    private final TextManager textManager = new TextManager(null);

    {
        this.newText();
    }

    public void newText() {
        LOGGER.info("Input raw text or a valid filename to load text from: ");
        String input = InputHandler.stringInput();
        // Check if input is a valid filename
        if (input.matches("^[\\w-]+\\.[A-Za-z]*$")) {
            textManager.setData(FileHandler.fileToString(input));
        } else {
            textManager.setData(input);
        }
    }

    private final String menu = """
            _____________AVAILABLE COMMANDS_____________\s
            new - input new text/load new text from file\s
            unique - count unique words in text\s
            chars - count chars in text\s
            search - search for word in text\s
            exit - close program\s
            _____________________________________________\s
            """;

    public void mainMenu() {
        while (true) {
            LOGGER.info(menu);
            switch (InputHandler.stringInput()) {
                case "new":
                    this.newText();
                    break;
                case "unique":
                    textManager.countUniqueWords();
                    break;
                case "chars":
                    textManager.countLetters();
                    break;
                case "search":
                    while (true) {
                        try {
                            LOGGER.info("Enter query: ");
                            textManager.searchWord(InputHandler.stringInput());
                            break;
                        } catch (ImpossibleQueryException e) {
                            LOGGER.info(e.getMessage());
                        }
                    }
                    break;
                case "exit":
                    System.exit(0);
                default:
                    LOGGER.info("Wrong command");
                    break;
            }
        }
    }
}
