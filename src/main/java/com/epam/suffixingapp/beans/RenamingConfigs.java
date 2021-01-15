package com.epam.suffixingapp.beans;

import java.nio.file.Path;
import java.util.List;

public class RenamingConfigs {
    private String suffix;
    private List<Path> files;

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public List<Path> getFiles() {
        return files;
    }

    public void setFiles(List<Path> files) {
        this.files = files;
    }
}
