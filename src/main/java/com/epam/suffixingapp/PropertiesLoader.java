package com.epam.suffixingapp;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesLoader {
    private static final String FILE_NOT_FOUND_FORMATTED = "File '%s' not found%n";

    private PropertiesLoader() {
    }

    public static Properties loadProperties(String path) {
        Properties properties = new Properties();
        try (Reader reader = Files.newBufferedReader(Paths.get(path))) {
            properties.load(reader);
        } catch (IOException e) {
            System.err.printf(FILE_NOT_FOUND_FORMATTED, path);
            System.exit(1);
        }
        return properties;
    }
}
