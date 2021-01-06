package com.epam.suffixingapp;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class App {
    private static final String NO_ARGUMENT_MESSAGE =
            "There is no any command line argument. Expected configuration file address";
    private static final String NOT_ALL_FILES_MESSAGE =
            "Not all files from configuration file exist";

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
        Map<Path, Path> renamingResult = Renamer.addSuffix(paths, suffix);
        ResultOutputPrinter.printResultOfRenamingToStdout(renamingResult);
    }

    private static void checkArgs(String[] args) {
        if (args.length < 1) {
            System.err.println(NO_ARGUMENT_MESSAGE);
            System.exit(1);
        }
    }
}
