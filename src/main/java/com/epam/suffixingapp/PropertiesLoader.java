package com.epam.suffixingapp;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static com.epam.suffixingapp.UserMessages.FILE_NOT_FOUND_FORMATTED;

public class PropertiesLoader {
    private PropertiesLoader() {
    }

    public static Properties loadProperties(String path) {
        Properties properties = new Properties();
        try (Reader reader = Files.newBufferedReader(Paths.get(path))) {
            properties.load(reader);
        } catch (IOException e) {
            System.err.printf(FILE_NOT_FOUND_FORMATTED, path);
            e.printStackTrace();
            System.exit(1);
        }
        return properties;
    }
}
