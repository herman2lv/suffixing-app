package com.epam.suffixingapp;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.epam.suffixingapp.UserMessages.PROPERTY_IS_NOT_CLEAR;

public class PropertiesReader {
    private static final String PROPERTY_FILES = "files";
    private static final String PROPERTY_SUFFIX = "suffix";
    private static final String FILES_PATHS_SPLIT_REGEX = ":";
    private final Properties properties;

    public PropertiesReader(Properties properties) {
        this.properties = properties;
    }

    public List<Path> getFiles() {
        String filesPathsString = properties.getProperty(PROPERTY_FILES);
        checkProperty(filesPathsString);
        String[] filesPaths = filesPathsString.split(FILES_PATHS_SPLIT_REGEX);
        List<Path> paths = new ArrayList<>();
        for (String filePath : filesPaths) {
            Path path = Paths.get(filePath);
            paths.add(path);
        }
        return paths;
    }

    public String getSuffix() {
        String suffix = properties.getProperty(PROPERTY_SUFFIX);
        checkProperty(suffix);
        return suffix;
    }

    private void checkProperty(String property) {
        if (property == null || property.isEmpty()) {
            throw new RuntimeException(PROPERTY_IS_NOT_CLEAR);
        }
    }
}
