package com.epam.suffixingapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Renamer {
    private Renamer() {
    }

    public static Map<Path, Path> addSuffix(List<Path> paths, String suffix) {
        Map<Path, Path> successfullyRenamedFiles = new HashMap<>();
        Path renamedFile;
        for (Path fileToRename : paths) {
            renamedFile = Paths.get(fileToRename.toString() + suffix);
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
