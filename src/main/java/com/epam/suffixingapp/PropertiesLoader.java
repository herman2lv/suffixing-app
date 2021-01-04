package com.epam.suffixingapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class PropertiesLoader {
    private static final String FILE_PATH_PROMPT = "Please, enter the configuration file full name: ";
    private static final Scanner SCANNER = new Scanner(System.in);

    public String getFilePath() {
        System.out.println(FILE_PATH_PROMPT);
        return SCANNER.next();
    }
    public Properties readProperties(String path) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(path)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There is no properties file \"" + path + "\"");
            System.exit(1);
        }
        return properties;
    }
}
