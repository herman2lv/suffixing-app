package com.epam.suffixingapp;

import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

import static com.epam.suffixingapp.UserMessages.NOT_ALL_FILES_MESSAGE;
import static com.epam.suffixingapp.UserMessages.NO_ARGUMENT_MESSAGE;

public class App {
    private App() {
    }

    public static void main(String[] args) {
        checkArgs(args);
        String propertiesFilePath = args[0];
        Properties properties = PropertiesLoader.loadProperties(propertiesFilePath);
        PropertiesReader propertiesReader = new PropertiesReader(properties);
        List<Path> paths = propertiesReader.getFiles();
        String suffix = propertiesReader.getSuffix();
        if (!FileChecker.checkFilesExistence(paths)) {
            System.err.println(NOT_ALL_FILES_MESSAGE);
            System.exit(1);
        }
        List<RenamingResult> renamingResults = Renamer.addSuffix(paths, suffix);
        ResultOutputPrinter.printResultOfRenamingToStdout(renamingResults);
    }

    private static void checkArgs(String[] args) {
        if (args.length < 1) {
            System.err.println(NO_ARGUMENT_MESSAGE);
            System.exit(1);
        }
    }
}
