package com.epam.suffixingapp;

import java.nio.file.Path;
import java.util.Map;
import java.util.Set;

public class ResultOutputPrinter {
    private static final String OUTPUT_SEPARATOR = " -> ";

    private ResultOutputPrinter() {
    }

    public static void printResultOfRenaming(Map<Path, Path> files) {
        System.out.println("Renamed files:");
        Set<Map.Entry<Path, Path>> setOfFiles = files.entrySet();
        for (Map.Entry<Path, Path> element : setOfFiles) {
            String outputLine = element.getKey().getFileName().toString()
                                + OUTPUT_SEPARATOR
                                + element.getValue().getFileName().toString();
            System.out.println(outputLine);
        }
        if (setOfFiles.isEmpty()) {
            System.out.println("none");
            System.exit(1);
        }
    }
}
