package com.epam.suffixingapp.services;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.epam.suffixingapp.TestPath.TEST_RESOURCES_PATH;

public class FileCheckerTest {
    private static final String FILE_1 = "testFile1";
    private static final String FILE_2 = "testFile2.txt";
    private static final String NO_FILE = "NoSuchFile";

    @Test
    public void checkFilesExistenceExists() {
        List<Path> files = new ArrayList<>();
        files.add(Paths.get(TEST_RESOURCES_PATH, FILE_1));
        Assert.assertTrue(FileChecker.checkFilesExistence(files));
    }

    @Test
    public void checkFilesExistenceNotExists() {
        List<Path> files = new ArrayList<>();
        files.add(Paths.get(TEST_RESOURCES_PATH, NO_FILE));
        Assert.assertFalse(FileChecker.checkFilesExistence(files));
    }

    @Test
    public void checkFilesExistenceNotAllFilesExists() {
        List<Path> files = new ArrayList<>();
        files.add(Paths.get(TEST_RESOURCES_PATH, FILE_1));
        files.add(Paths.get(TEST_RESOURCES_PATH, NO_FILE));
        Assert.assertFalse(FileChecker.checkFilesExistence(files));
    }

    @Test
    public void checkFilesExistenceAllFilesExists() {
        List<Path> files = new ArrayList<>();
        files.add(Paths.get(TEST_RESOURCES_PATH, FILE_1));
        files.add(Paths.get(TEST_RESOURCES_PATH, FILE_2));
        Assert.assertTrue(FileChecker.checkFilesExistence(files));
    }
}
