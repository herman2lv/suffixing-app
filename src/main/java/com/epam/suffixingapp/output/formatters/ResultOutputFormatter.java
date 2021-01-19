package com.epam.suffixingapp.output.formatters;

import com.epam.suffixingapp.beans.RenamingResult;

import java.util.List;

public interface ResultOutputFormatter {
    String format(List<RenamingResult> files, String configFilePath);
}
