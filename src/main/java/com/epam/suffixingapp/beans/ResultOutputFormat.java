package com.epam.suffixingapp.beans;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class ResultOutputFormat {
    private final String configFilePath;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final Date timeOfOperation;
    private final List<RenamingResult> renamedFiles;

    public ResultOutputFormat(String configFilePath, List<RenamingResult> renamedFiles) {
        this.configFilePath = configFilePath;
        this.timeOfOperation = new Date();
        this.renamedFiles = renamedFiles;
    }

    public String getConfigFilePath() {
        return configFilePath;
    }

    public Date getTimeOfOperation() {
        return timeOfOperation;
    }

    public List<RenamingResult> getRenamedFiles() {
        return renamedFiles;
    }
}
