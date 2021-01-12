package com.epam.suffixingapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static com.epam.suffixingapp.UserMessages.FILE_NOT_FOUND_FORMATTED;

public class PropertiesLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesLoader.class);

    private PropertiesLoader() {
    }

    public static Properties loadProperties(String path) {
        Properties properties = new Properties();
        try (Reader reader = Files.newBufferedReader(Paths.get(path))) {
            properties.load(reader);
            LOGGER.info("Properties file has been loaded successfully");
        } catch (IOException e) {
            System.err.printf(FILE_NOT_FOUND_FORMATTED, path);
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return properties;
    }
}
