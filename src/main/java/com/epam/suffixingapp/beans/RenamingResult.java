package com.epam.suffixingapp.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.nio.file.Path;

public class RenamingResult {
    private final Path oldPath;
    private final Path newPath;

    public RenamingResult(Path oldPath, Path newPath) {
        this.oldPath = oldPath;
        this.newPath = newPath;
    }

    @JsonProperty("oldName")
    public Path getOldPath() {
        return oldPath;
    }

    @JsonProperty("newName")
    public Path getNewPath() {
        return newPath;
    }
}
