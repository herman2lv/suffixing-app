package com.epam.suffixingapp;

import java.nio.file.Path;

public class RenamingResult {
    private final Path oldPath;
    private final Path newPath;

    public RenamingResult(Path oldPath, Path newPath) {
        this.oldPath = oldPath;
        this.newPath = newPath;
    }

    public Path getOldPath() {
        return oldPath;
    }

    public Path getNewPath() {
        return newPath;
    }
}
