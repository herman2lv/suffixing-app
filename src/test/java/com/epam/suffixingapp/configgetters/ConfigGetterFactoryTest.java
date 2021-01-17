package com.epam.suffixingapp.configgetters;

import org.junit.Assert;
import org.junit.Test;

public class ConfigGetterFactoryTest {
    private static final String JSON_FILE_PATH = "/file.json";
    private static final String PROPERTIES_FILE_PATH = "/file.properties";
    private static final String XML_FILE_PATH = "/file.xml";
    private static final String UNKNOWN_FILE_PATH = "/file";
    @Test
    public void CreateJsonConfigGetterTest() {
        ConfigGetter getter = ConfigGetterFactory.createConfigGetter(JSON_FILE_PATH);
        Assert.assertEquals(JsonGetter.class, getter.getClass());
    }

    @Test
    public void CreateXmlConfigGetterTest() {
        ConfigGetter getter = ConfigGetterFactory.createConfigGetter(XML_FILE_PATH);
        Assert.assertEquals(XmlGetter.class, getter.getClass());
    }

    @Test
    public void CreatePropertiesConfigGetterTest() {
        ConfigGetter getter = ConfigGetterFactory.createConfigGetter(PROPERTIES_FILE_PATH);
        Assert.assertEquals(PropertiesGetter.class, getter.getClass());
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateUnknownTypeConfigGetterTest() {
        ConfigGetter getter = ConfigGetterFactory.createConfigGetter(UNKNOWN_FILE_PATH);
    }
}
