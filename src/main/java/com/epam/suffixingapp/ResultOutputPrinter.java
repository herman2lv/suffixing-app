package com.epam.suffixingapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.epam.suffixingapp.UserMessages.FAILED_TO_RENAME;
import static com.epam.suffixingapp.UserMessages.RESULT_OUTPUT_FORMAT;

public class ResultOutputPrinter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResultOutputPrinter.class);

    private ResultOutputPrinter() {
    }

    public static void printResultOfRenamingToStdout(List<RenamingResult> files) {
        if (files.isEmpty()) {
            LOGGER.error(FAILED_TO_RENAME);
            throw new RuntimeException(FAILED_TO_RENAME);
        }
        files.forEach(s -> System.out.printf(RESULT_OUTPUT_FORMAT,
                s.getOldPath().getFileName(), s.getNewPath().getFileName()));
        LOGGER.info("Renaming results have been printed to the stdout");
    }
}
