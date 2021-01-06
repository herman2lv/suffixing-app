package com.epam.suffixingapp;

import java.nio.file.Path;
import java.util.Map;
import java.util.Set;

public class ResultOutputPrinter {
    private static final String OUTPUT_FORMAT = "Renamed: %s -> %s%n";
    private static final String FAILED_TO_RENAME = "[FAIL] Renamed: NONE";

    private ResultOutputPrinter() {
    }

    public static void printResultOfRenamingToStdout(Map<Path, Path> files) {
        Set<Map.Entry<Path, Path>> setOfFiles = files.entrySet();
        for (Map.Entry<Path, Path> element : setOfFiles) {
            System.out.printf(OUTPUT_FORMAT, element.getKey().getFileName(),
                    element.getValue().getFileName());
        }
        if (setOfFiles.isEmpty()) {
            System.out.println(FAILED_TO_RENAME);
            System.exit(1);
        }
    }
}
