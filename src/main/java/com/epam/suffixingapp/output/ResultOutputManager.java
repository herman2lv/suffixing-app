package com.epam.suffixingapp.output;

import com.epam.suffixingapp.beans.RenamingResult;
import com.epam.suffixingapp.enums.OutputFileType;
import com.epam.suffixingapp.output.formatters.ResultOutputFormatter;
import com.epam.suffixingapp.output.formatters.ResultOutputFormatterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.util.List;

import static com.epam.suffixingapp.messages.UserMessages.LOG_PRINTED_TO_STDOUT;
import static com.epam.suffixingapp.messages.UserMessages.LOG_SAVED_TO_JSON;
import static com.epam.suffixingapp.messages.UserMessages.LOG_SAVED_TO_XML;

public class ResultOutputManager {
    private static final Logger CONSOLE_OUTPUT = LoggerFactory.getLogger("resultOutput");
    private static final Logger LOGGER = LoggerFactory.getLogger(ResultOutputManager.class);
    private static final String OUTPUT_DIR_PATH =
            "/home/herman/IdeaProjects/suffixing-app/target/";
    private static final String OUTPUT_XML_FILE = "renamingOutput.xml";
    private static final String OUTPUT_JSON_FILE = "renamingOutput.json";

    private ResultOutputManager() {
    }

    public static void outputResult(
            List<RenamingResult> files, String configFilePath, String outputFileTypeString) {
        OutputFileType outputFileType = getOutputFileType(outputFileTypeString);
        ResultOutputFormatter formatter =
                ResultOutputFormatterFactory.createFormatter(outputFileType);
        String output = formatter.format(files, configFilePath);
        switch (outputFileType) {
            case JSON -> {
                ResultOutputSaver
                        .saveToFile(Paths.get(OUTPUT_DIR_PATH, OUTPUT_JSON_FILE), output);
                LOGGER.info(LOG_SAVED_TO_JSON);
            }
            case XML -> {
                ResultOutputSaver
                        .saveToFile(Paths.get(OUTPUT_DIR_PATH, OUTPUT_XML_FILE), output);
                LOGGER.info(LOG_SAVED_TO_XML);
            }
            default -> {
                CONSOLE_OUTPUT.info(output);
                LOGGER.info(LOG_PRINTED_TO_STDOUT);
            }
        }
    }

    private static OutputFileType getOutputFileType(String outputFileTypeString) {
        return switch (outputFileTypeString.toLowerCase()) {
            case "json" -> OutputFileType.JSON;
            case "xml" -> OutputFileType.XML;
            default -> OutputFileType.NONE;
        };
    }
}
