package com.epam.suffixingapp.output.formatters;

import com.epam.suffixingapp.beans.RenamingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.epam.suffixingapp.messages.UserMessages.CONFIG_FILE_PATH_FORMATTED;
import static com.epam.suffixingapp.messages.UserMessages.FAILED_TO_RENAME;
import static com.epam.suffixingapp.messages.UserMessages.RESULT_OUTPUT_FORMAT;

public class SimpleFormatter implements ResultOutputFormatter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleFormatter.class);

    @Override
    public String format(List<RenamingResult> files, String configFilePath) {
        if (files.isEmpty()) {
            LOGGER.error(FAILED_TO_RENAME);
            return FAILED_TO_RENAME;
        }
        StringBuilder resultOutput = new StringBuilder();
        resultOutput.append(String.format(CONFIG_FILE_PATH_FORMATTED, configFilePath));
        files.forEach(s -> resultOutput.append(String.format(
                RESULT_OUTPUT_FORMAT,
                s.getOldPath().getFileName(),
                s.getNewPath().getFileName())));
        return resultOutput.toString();
    }
}
