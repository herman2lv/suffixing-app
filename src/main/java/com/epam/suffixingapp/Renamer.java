package com.epam.suffixingapp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Renamer {
    private Renamer() {
    }

    public static boolean checkFileExistence(String[] files) {
        boolean allFilesExist = true;
        for (String filePath : files) {
            File file = new File(filePath);
            if (!file.exists() || file.isDirectory()) {
                System.out.println("There is no file \"" + filePath
                                   + "\" that was mentioned in configuration file");
                allFilesExist = false;
            }
        }
        return allFilesExist;
    }

    public static Map<Path, Path> rename(String[] files, String suffix) {
        Map<Path, Path> successfullyRenamedFiles = new HashMap<>();
        Path fileToRename;
        Path renamedFile;
        for (String filePath : files) {
            fileToRename = Paths.get(filePath);
            renamedFile = Paths.get(filePath + suffix);
            try {
                Files.move(fileToRename, renamedFile);
                successfullyRenamedFiles.put(fileToRename, renamedFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return successfullyRenamedFiles;
    }
}
