package com.epam.suffixingapp.output;

import com.epam.suffixingapp.beans.RenamingResult;
import com.epam.suffixingapp.beans.ResultOutputFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.epam.suffixingapp.messages.UserMessages.FAILED_TO_RENAME;
import static com.epam.suffixingapp.messages.UserMessages.LOG_JSON_FORMATTING_FAILED;
import static com.epam.suffixingapp.messages.UserMessages.RESULT_OUTPUT_FORMAT;

public class ResultOutputFormatter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResultOutputFormatter.class);

    private ResultOutputFormatter() {
    }

    public static String formatResultOfRenamingToJsonString(
            List<RenamingResult> files, String configFilePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        ResultOutputFormat outputFormat = new ResultOutputFormat(configFilePath, files);
        try {
            return objectMapper.writeValueAsString(outputFormat);
        } catch (JsonProcessingException e) {
            LOGGER.error(LOG_JSON_FORMATTING_FAILED, e);
            e.printStackTrace();
            return LOG_JSON_FORMATTING_FAILED;
        }
    }

    public static String formatResultOfRenamingToXMLString(List<RenamingResult> files) {
        return null;
    }

    public static String saveResultOfRenamingToString(List<RenamingResult> files) {
        if (files.isEmpty()) {
            LOGGER.error(FAILED_TO_RENAME);
            return FAILED_TO_RENAME;
        }
        StringBuilder resultOutput = new StringBuilder();
        files.forEach(s -> resultOutput.append(String.format(
                RESULT_OUTPUT_FORMAT,
                s.getOldPath().getFileName(),
                s.getNewPath().getFileName())));
        return resultOutput.toString();
    }
}
