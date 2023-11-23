package com.solvd.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileHandler {
    public static final String OUTPUT = "output.txt";
    private static final Logger LOGGER = LogManager.getLogger(FileHandler.class);

    // Create or truncate existing output.txt on class load
    static {
        try {
            Files.write(Path.of(OUTPUT), "".getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            LOGGER.info("Failed to create output.txt due to IO error.");
            System.exit(1);
        }
    }

    public static String getOutput() {
        return OUTPUT;
    }

    public static String fileToString(String filepath) {
        try {
            return new String(Files.readAllBytes(Path.of(filepath)));
        } catch (IOException e) {
            LOGGER.info("Unable to read provided file.");
            System.exit(1);
        }
        return null;
    }

    public static boolean stringToFile(String data) {
        try {
            Path path = Path.of(OUTPUT);
            Files.write(path, data.getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            LOGGER.info("Unable to save file.");
        }
        return false;
    }
}
