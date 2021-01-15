package com.epam.suffixingapp.output;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static com.epam.suffixingapp.messages.UserMessages.LOG_PRINTED_TO_STDOUT;

public class ResultOutputPrinter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResultOutputPrinter.class);

    private ResultOutputPrinter() {
    }

    public static void printResultOfRenamingToStdout(String resultOutput) {
        System.out.println(resultOutput);
        LOGGER.info(LOG_PRINTED_TO_STDOUT);
    }

    public static void saveResultOfRenamingToFile(Path file, String resultOutput) {
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
