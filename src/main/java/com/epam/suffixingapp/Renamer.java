package com.epam.suffixingapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Renamer {
    private Renamer() {
    }

    public static List<RenamingResult> addSuffix(List<Path> paths, String suffix) {
        List<RenamingResult> successfullyRenamedFiles = new ArrayList<>();
        for (Path fileToRename : paths) {
            Path renamedFile = Paths.get(fileToRename.toString() + suffix);
            try {
                Files.move(fileToRename, renamedFile);
                successfullyRenamedFiles.add(new RenamingResult(fileToRename, renamedFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return successfullyRenamedFiles;
    }
}
