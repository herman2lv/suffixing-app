package com.epam.suffixingapp.configgetters;

import com.epam.suffixingapp.beans.RenamingConfigs;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JsonGetter implements ConfigGetter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonGetter.class);

    @Override
    public RenamingConfigs getConfigs(String path) {
        try (Reader reader = new FileReader(path)) {
            return new ObjectMapper().readValue(reader, RenamingConfigs.class);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
