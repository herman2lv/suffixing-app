package com.epam.suffixingapp.services;

import com.epam.suffixingapp.beans.RenamingConfigs;
import com.epam.suffixingapp.beans.RenamingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.epam.suffixingapp.messages.UserMessages.LOG_FILE_RENAMED_FORMATTED;

public class Renamer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Renamer.class);

    private Renamer() {
    }

    public static List<RenamingResult> addSuffix(RenamingConfigs configs) {
        List<RenamingResult> successfullyRenamedFiles = new ArrayList<>();
        for (Path fileToRename : configs.getFiles()) {
            Path renamedFile = Paths.get(fileToRename.toString() + configs.getSuffix());
            try {
                Files.move(fileToRename, renamedFile);
                successfullyRenamedFiles.add(new RenamingResult(fileToRename, renamedFile));
                LOGGER.info(LOG_FILE_RENAMED_FORMATTED,
                        fileToRename.getFileName(), renamedFile.getFileName());
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return successfullyRenamedFiles;
    }
}
