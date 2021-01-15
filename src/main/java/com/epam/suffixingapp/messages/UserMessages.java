package com.epam.suffixingapp.messages;

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
    public static final String FILE_TYPE_IS_UNKNOWN = "Config file type is unknown";
    public static final String LOG_APP_STARTED = "App has been started";
    public static final String LOG_PROPS_LOADED = "Properties file has been loaded successfully";
    public static final String LOG_PRINTED_TO_STDOUT =
            "Renaming results have been printed to the standard output stream";
    public static final String LOG_FILE_RENAMED_FORMATTED = "File {} was renamed to {}";
    public static final String LOG_APP_FINISHED = "App has been finished correctly";
    public static final String LOG_JSON_FORMATTING_FAILED =
            "Result output set can't be marshalled to JSON string";

    private UserMessages() {
    }
}
