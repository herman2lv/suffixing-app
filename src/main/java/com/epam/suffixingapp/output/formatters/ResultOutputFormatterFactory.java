package com.epam.suffixingapp.output.formatters;

import com.epam.suffixingapp.enums.OutputFileType;

public class ResultOutputFormatterFactory {
    private ResultOutputFormatterFactory() {
    }

    public static ResultOutputFormatter createFormatter(OutputFileType outputFormat) {
        return switch (outputFormat) {
            case JSON -> new JsonFormatter();
            case XML -> new XMLFormatter();
            default -> new SimpleFormatter();
        };
    }
}
