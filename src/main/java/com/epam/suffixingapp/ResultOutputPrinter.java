package com.epam.suffixingapp;

import java.nio.file.Path;
import java.util.Map;

import static com.epam.suffixingapp.UserMessages.FAILED_TO_RENAME;

public class ResultOutputPrinter {
    private static final String OUTPUT_FORMAT = "Renamed: %s -> %s%n";

    private ResultOutputPrinter() {
    }

    public static void printResultOfRenamingToStdout(Map<Path, Path> files) {
        if (files.isEmpty()) {
            System.out.println(FAILED_TO_RENAME);
            System.exit(1);
        }
        files.forEach((s1, s2) -> System.out.printf(OUTPUT_FORMAT,
                s1.getFileName(), s2.getFileName()));
    }
}
