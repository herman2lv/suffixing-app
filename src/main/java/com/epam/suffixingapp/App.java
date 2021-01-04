package com.epam.suffixingapp;

import java.nio.file.Path;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        String configurationFilePath = propertiesLoader.getFilePath();
        PropertiesReader propertiesReader =
                new PropertiesReader(propertiesLoader.readProperties(configurationFilePath));
        String[] filesToRename = propertiesReader.getFiles();
        String suffix = propertiesReader.getSuffix();
        if (!Renamer.checkFileExistence(filesToRename)) {
            System.exit(1);
        }
        Map<Path, Path> renamedFiles = Renamer.rename(filesToRename, suffix);
        ResultOutputPrinter.printResultOfRenaming(renamedFiles);
    }

    private App() {
    }
}
