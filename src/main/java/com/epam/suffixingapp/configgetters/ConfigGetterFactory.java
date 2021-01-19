package com.epam.suffixingapp.configgetters;

import com.epam.suffixingapp.enums.ConfigFileType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.suffixingapp.messages.UserMessages.FILE_TYPE_IS_UNKNOWN;

public class ConfigGetterFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigGetterFactory.class);
    private static final String REGEX_JSON = ".*\\.json";
    private static final String REGEX_XML = ".*\\.xml";
    private static final String REGEX_PROPERTIES = ".*\\.properties";

    private ConfigGetterFactory() {
    }

    public static ConfigGetter createConfigGetter(String filePath) {
        ConfigFileType configFileType = getConfigFileType(filePath);
        return switch (configFileType) {
            case XML -> new XmlGetter();
            case JSON -> new JsonGetter();
            case PROPERTIES -> new PropertiesGetter();
        };
    }

    private static ConfigFileType getConfigFileType(String filePath) {
        if (filePath.matches(REGEX_JSON)) {
            return ConfigFileType.JSON;
        } else if (filePath.matches(REGEX_XML)) {
            return ConfigFileType.XML;
        } else if (filePath.matches(REGEX_PROPERTIES)) {
            return ConfigFileType.PROPERTIES;
        } else {
            LOGGER.error(FILE_TYPE_IS_UNKNOWN);
            throw new IllegalArgumentException(FILE_TYPE_IS_UNKNOWN);
        }
    }
}
