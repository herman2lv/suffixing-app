package com.epam.suffixingapp.configgetters;

import com.epam.suffixingapp.beans.RenamingConfigs;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static com.epam.suffixingapp.TestPath.TEST_RESOURCES_PATH;

public class PropertiesGetterTest {
    private static final String TEST_CONFIG_PROPERTIES = "testConfig.properties";
    private static final String FILES_KEY = "files=";
    private static final String FILES_VALUE = "/file/to/rename";
    private static final String SUFFIX_KEY = "suffix=";
    private static final String SUFFIX_VALUE = ".txt";
    private static final String OUTPUT_KEY = "output=";
    private static final String OUTPUT_VALUE = "json";

    @Before
    public void setUp() throws Exception {
        String testConfigContent = FILES_KEY + FILES_VALUE + "\n"
                                   + SUFFIX_KEY + SUFFIX_VALUE + "\n"
                                   + OUTPUT_KEY + OUTPUT_VALUE + "\n";
        Files.writeString(Paths.get(TEST_RESOURCES_PATH + TEST_CONFIG_PROPERTIES),
                testConfigContent, StandardOpenOption.WRITE);
    }

    @Test
    public void getConfigsTest() {
        String configFilePath = TEST_RESOURCES_PATH + TEST_CONFIG_PROPERTIES;
        PropertiesGetter propertiesGetter = new PropertiesGetter();
        RenamingConfigs configs = propertiesGetter.getConfigs(configFilePath);
        Assert.assertEquals(FILES_VALUE, configs.getFiles().get(0).toString());
        Assert.assertEquals(SUFFIX_VALUE, configs.getSuffix());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getConfigsFromNotExistingFile() {
        String configFilePath = TEST_RESOURCES_PATH + "NoSuchFile";
        new PropertiesGetter().getConfigs(configFilePath);
    }
}
