package com.epam.suffixingapp;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileChecker {
    private FileChecker() {
    }

    public static boolean checkFilesExistence(List<Path> paths) {
        boolean allFilesExist = true;
        for (Path path : paths) {
            if (Files.notExists(path) || Files.isDirectory(path)) {
                allFilesExist = false;
            }
        }
        return allFilesExist;
    }
}
