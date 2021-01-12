package com.epam.suffixingapp;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileCheckerTest {
    static final String TEST_RESOURCES_PATH =
            "/home/herman/IdeaProjects/suffixing-app/src/test/resources/";

    @Test
    public void checkFilesExistenceExists() {
        List<Path> files = new ArrayList<>();
        files.add(Paths.get(TEST_RESOURCES_PATH, "testFile1"));
        Assert.assertTrue(FileChecker.checkFilesExistence(files));
    }

    @Test
    public void checkFilesExistenceNotExists() {
        List<Path> files = new ArrayList<>();
        files.add(Paths.get(TEST_RESOURCES_PATH, "NoSuchFile"));
        Assert.assertFalse(FileChecker.checkFilesExistence(files));
    }

    @Test
    public void checkFilesExistenceNotAllFilesExists() {
        List<Path> files = new ArrayList<>();
        files.add(Paths.get(TEST_RESOURCES_PATH, "testFile1"));
        files.add(Paths.get(TEST_RESOURCES_PATH, "NoSuchFile"));
        Assert.assertFalse(FileChecker.checkFilesExistence(files));
    }

    @Test
    public void checkFilesExistenceAllFilesExists() {
        List<Path> files = new ArrayList<>();
        files.add(Paths.get(TEST_RESOURCES_PATH, "testFile1"));
        files.add(Paths.get(TEST_RESOURCES_PATH, "testFile2.txt"));
        Assert.assertTrue(FileChecker.checkFilesExistence(files));
    }
}
