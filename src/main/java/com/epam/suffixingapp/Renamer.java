package com.epam.suffixingapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Renamer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Renamer.class);

    private Renamer() {
    }

    public static List<RenamingResult> addSuffix(List<Path> paths, String suffix) {
        List<RenamingResult> successfullyRenamedFiles = new ArrayList<>();
        for (Path fileToRename : paths) {
            Path renamedFile = Paths.get(fileToRename.toString() + suffix);
            try {
                Files.move(fileToRename, renamedFile);
                successfullyRenamedFiles.add(new RenamingResult(fileToRename, renamedFile));
                LOGGER.info("File {} was renamed to {}",
                        fileToRename.getFileName(), renamedFile.getFileName());
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
                e.printStackTrace();
            }
        }
        return successfullyRenamedFiles;
    }
}
