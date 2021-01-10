package com.epam.suffixingapp;

import org.junit.Test;

import static com.epam.suffixingapp.FileCheckerTest.TEST_RESOURCES_PATH;

public class PropertiesLoaderTest {
    @Test
    public void loadExistingProperties() {
        String configFilePath = TEST_RESOURCES_PATH + "testConfig.properties";
        PropertiesLoader.loadProperties(configFilePath);
    }

    @Test(expected = RuntimeException.class)
    public void loadNotExistingProperties() {
        String configFilePath = TEST_RESOURCES_PATH + "NoSuchFile";
        PropertiesLoader.loadProperties(configFilePath);
    }
}
