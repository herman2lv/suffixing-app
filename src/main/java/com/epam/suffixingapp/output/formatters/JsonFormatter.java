package com.epam.suffixingapp.output.formatters;

import com.epam.suffixingapp.beans.RenamingResult;
import com.epam.suffixingapp.beans.ResultOutputFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.epam.suffixingapp.messages.UserMessages.LOG_JSON_FORMATTING_FAILED;

public class JsonFormatter implements ResultOutputFormatter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonFormatter.class);

    @Override
    public String format(List<RenamingResult> files, String configFilePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        ResultOutputFormat outputFormat = new ResultOutputFormat(configFilePath, files);
        try {
            return objectMapper.writeValueAsString(outputFormat);
        } catch (JsonProcessingException e) {
            LOGGER.error(LOG_JSON_FORMATTING_FAILED, e);
            return LOG_JSON_FORMATTING_FAILED;
        }
    }
}
