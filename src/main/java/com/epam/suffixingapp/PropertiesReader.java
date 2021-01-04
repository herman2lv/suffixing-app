package com.epam.suffixingapp;

import java.util.Properties;

public class PropertiesReader {
    private final Properties properties;

    public PropertiesReader(Properties properties) {
        this.properties = properties;
    }

    public String[] getFiles() {
        String files = properties.getProperty("files");
        checkProperty(files);
        return files.split(":");
    }

    public String getSuffix() {
        String suffix = properties.getProperty("suffix");
        checkProperty(suffix);
        return suffix;
    }

    private void checkProperty(String property) {
        if (property == null || property.isEmpty()) {
            System.out.println("Properties file is not configured properly");
            System.exit(1);
        }
    }
}
