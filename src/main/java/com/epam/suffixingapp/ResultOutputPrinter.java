package com.epam.suffixingapp;

import java.util.List;

import static com.epam.suffixingapp.UserMessages.FAILED_TO_RENAME;
import static com.epam.suffixingapp.UserMessages.RESULT_OUTPUT_FORMAT;

public class ResultOutputPrinter {
    private ResultOutputPrinter() {
    }

    public static void printResultOfRenamingToStdout(List<RenamingResult> files) {
        if (files.isEmpty()) {
            System.out.println(FAILED_TO_RENAME);
            System.exit(1);
        }
        files.forEach((s) -> System.out.printf(RESULT_OUTPUT_FORMAT,
                s.getOldPath().getFileName(), s.getNewPath().getFileName()));
    }
}
