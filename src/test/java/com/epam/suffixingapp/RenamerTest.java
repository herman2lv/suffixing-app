package com.epam.suffixingapp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.epam.suffixingapp.FileCheckerTest.TEST_RESOURCES_PATH;

public class RenamerTest {
    private final List<Path> files = new ArrayList<>();
    private static final String FILE_NAME = "fileToRename";
    private static final String SUFFIX = ".txt";

    @Before
    public void setUp() throws Exception {
        Path fileToRename = Paths.get(TEST_RESOURCES_PATH, FILE_NAME);
        Files.createFile(fileToRename);
        files.add(fileToRename);
    }

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(files.get(0));
        Files.deleteIfExists(Paths.get(files.get(0).toString() + SUFFIX));
    }

    @Test
    public void renameCorrect() {
        List<RenamingResult> resultList = Renamer.addSuffix(files, SUFFIX);
        Assert.assertEquals(files.get(0).getFileName().toString(),
                resultList.get(0).getOldPath().getFileName().toString());
        Assert.assertEquals(files.get(0).getFileName().toString() + SUFFIX,
                resultList.get(0).getNewPath().getFileName().toString());
    }

    @Test
    public void renameNotExistingFile() {
        List<Path> notExistingFiles = new ArrayList<>();
        notExistingFiles.add(Paths.get("NoSuchFile"));
        List<RenamingResult> resultList = Renamer.addSuffix(notExistingFiles, SUFFIX);
        Assert.assertTrue(resultList.isEmpty());
    }
}
