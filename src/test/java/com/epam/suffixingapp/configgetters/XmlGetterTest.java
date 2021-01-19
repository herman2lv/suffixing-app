package com.epam.suffixingapp.configgetters;

import com.epam.suffixingapp.beans.RenamingConfigs;
import org.junit.Assert;
import org.junit.Test;

import static com.epam.suffixingapp.TestPath.TEST_RESOURCES_PATH;

public class XmlGetterTest {
    private static final String TEST_CONFIG_XML = "testConfig.xml";
    public static final String SUFFIX = ".test-suf";
    public static final String TEST_FILE_PATH = "/home/herman/IdeaProjects/suffixing-app/files/1";

    @Test
    public void getConfigsTest() {
        XmlGetter xmlGetter = new XmlGetter();
        RenamingConfigs configsFromFile =
                xmlGetter.getConfigs(TEST_RESOURCES_PATH + TEST_CONFIG_XML);
        Assert.assertEquals(SUFFIX, configsFromFile.getSuffix());
        Assert.assertEquals(TEST_FILE_PATH, configsFromFile.getFiles().get(0).toString());
    }

    @Test(expected = RuntimeException.class)
    public void getConfigsFromNotExistingFile() {
        String configFilePath = TEST_RESOURCES_PATH + "NoSuchFile";
        new XmlGetter().getConfigs(configFilePath);
    }
}
