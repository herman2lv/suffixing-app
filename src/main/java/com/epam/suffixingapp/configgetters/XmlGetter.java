package com.epam.suffixingapp.configgetters;

import com.epam.suffixingapp.beans.RenamingConfigs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlGetter implements ConfigGetter {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlGetter.class);

    @Override
    public RenamingConfigs getConfigs(String path) {
        RenamingConfigs configs = null;
        return configs;
    }
}
