package com.epam.suffixingapp.services;

import com.epam.suffixingapp.beans.RenamingConfigs;
import com.epam.suffixingapp.beans.RenamingResult;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.epam.suffixingapp.TestPath.TEST_RESOURCES_PATH;

public class RenamerTest {
    private final RenamingConfigs configs = new RenamingConfigs();
    private static final String FILE_NAME = "fileToRename";
    private static final String SUFFIX = ".txt";

    @Before
    public void setUp() throws Exception {
        Path fileToRename = Paths.get(TEST_RESOURCES_PATH, FILE_NAME);
        Files.createFile(fileToRename);
        List<Path> files = new ArrayList<>();
        files.add(fileToRename);
        configs.setFiles(files);
        configs.setSuffix(SUFFIX);
    }

    @After
    public void tearDown() throws Exception {
        for (Path file : configs.getFiles()) {
            Files.deleteIfExists(file);
            Files.deleteIfExists(Paths.get(file.toString() + SUFFIX));
        }
    }

    @Test
    public void renameCorrect() {
        List<RenamingResult> resultList = Renamer.addSuffix(configs);
        for (int i = 0; i < configs.getFiles().size(); i++) {
            Assert.assertEquals(configs.getFiles().get(i).getFileName().toString(),
                    resultList.get(i).getOldPath().getFileName().toString());
            Assert.assertEquals(configs.getFiles().get(i).getFileName().toString() + SUFFIX,
                    resultList.get(i).getNewPath().getFileName().toString());
        }
    }

    @Test
    public void renameNotExistingFile() {
        RenamingConfigs notExistingFiles = new RenamingConfigs();
        List<Path> notExistingFilesList = new ArrayList<>();
        notExistingFilesList.add(Paths.get("NoSuchFile"));
        notExistingFiles.setFiles(notExistingFilesList);
        notExistingFiles.setSuffix(SUFFIX);
        List<RenamingResult> resultList = Renamer.addSuffix(notExistingFiles);
        Assert.assertTrue(resultList.isEmpty());
    }
}
