package com.epam.suffixingapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

import static com.epam.suffixingapp.UserMessages.NOT_ALL_FILES_MESSAGE;
import static com.epam.suffixingapp.UserMessages.NO_ARGUMENT_MESSAGE;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    private App() {
    }

    public static void main(String[] args) {
        LOGGER.debug("App has been started");
        checkArgs(args);
        String propertiesFilePath = args[0];
        Properties properties = PropertiesLoader.loadProperties(propertiesFilePath);
        PropertiesReader propertiesReader = new PropertiesReader(properties);
        List<Path> paths = propertiesReader.getFiles();
        String suffix = propertiesReader.getSuffix();
        if (!FileChecker.checkFilesExistence(paths)) {
            System.err.println(NOT_ALL_FILES_MESSAGE);
            LOGGER.error(NOT_ALL_FILES_MESSAGE);
            System.exit(1);
        }
        List<RenamingResult> renamingResults = Renamer.addSuffix(paths, suffix);
        ResultOutputPrinter.printResultOfRenamingToStdout(renamingResults);
        LOGGER.debug("App has been finished correctly");
    }

    private static void checkArgs(String[] args) {
        if (args.length < 1) {
            LOGGER.error(NO_ARGUMENT_MESSAGE);
            throw new RuntimeException(NO_ARGUMENT_MESSAGE);
        }
    }
}
