package com.epam.suffixingapp.configgetters;

import com.epam.suffixingapp.beans.RenamingConfigs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.epam.suffixingapp.messages.UserMessages.FILE_NOT_FOUND_FORMATTED;
import static com.epam.suffixingapp.messages.UserMessages.LOG_PROPS_LOADED;
import static com.epam.suffixingapp.messages.UserMessages.PROPERTY_IS_NOT_CLEAR;

public class PropertiesGetter implements ConfigGetter {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesGetter.class);
    private static final String PROPERTY_FILES = "files";
    private static final String PROPERTY_SUFFIX = "suffix";
    private static final String FILES_PATHS_SPLIT_REGEX = ":";

    @Override
    public RenamingConfigs getConfigs(String path) {
        Properties properties = loadProperties(path);
        RenamingConfigs configs = new RenamingConfigs();
        configs.setSuffix(getSuffix(properties));
        configs.setFiles(getFiles(properties));
        return configs;
    }

    private Properties loadProperties(String path) {
        Properties properties = new Properties();
        try (Reader reader = Files.newBufferedReader(Paths.get(path))) {
            properties.load(reader);
            LOGGER.info(LOG_PROPS_LOADED);
        } catch (IOException e) {
            System.err.printf(FILE_NOT_FOUND_FORMATTED, path);
            LOGGER.error(e.getMessage(), e);
            throw new IllegalArgumentException(e);
        }
        return properties;
    }

    private List<Path> getFiles(Properties properties) {
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

    private String getSuffix(Properties properties) {
        String suffix = properties.getProperty(PROPERTY_SUFFIX);
        checkProperty(suffix);
        return suffix;
    }

    private void checkProperty(String property) {
        if (property == null || property.isEmpty()) {
            LOGGER.error(PROPERTY_IS_NOT_CLEAR);
            throw new IllegalArgumentException(PROPERTY_IS_NOT_CLEAR);
        }
    }
}
