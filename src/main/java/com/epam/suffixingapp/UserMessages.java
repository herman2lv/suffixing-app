package com.epam.suffixingapp;

public class UserMessages {
    public static final String PROPERTY_IS_NOT_CLEAR =
            "Properties file is not configured properly";
    public static final String NO_ARGUMENT_MESSAGE =
            "There is no any command line argument. Expected configuration file address";
    public static final String NOT_ALL_FILES_MESSAGE =
            "Not all files from configuration file exist";
    public static final String FAILED_TO_RENAME = "Renamed: NONE";
    public static final String RESULT_OUTPUT_FORMAT = "Renamed: %s -> %s%n";
    public static final String FILE_NOT_FOUND_FORMATTED = "File '%s' not found%n";

    private UserMessages() {
    }
}
