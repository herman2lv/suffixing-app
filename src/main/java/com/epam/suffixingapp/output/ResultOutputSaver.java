package com.epam.suffixingapp.output;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ResultOutputSaver {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResultOutputSaver.class);

    private ResultOutputSaver() {
    }

    public static void saveToFile(Path file, String resultOutput) {
        try {
            Files.deleteIfExists(file);
            Files.createFile(file);
            Files.writeString(file, resultOutput, StandardOpenOption.WRITE);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
