package com.epam.suffixingapp.configgetters;

import com.epam.suffixingapp.beans.RenamingConfigs;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static com.epam.suffixingapp.TestPath.TEST_RESOURCES_PATH;

public class JsonGetterTest {
    private static final String TEST_CONFIG_JSON = "testConfig.json";
    private static final String FILE_1 = "/file/to/rename/1";
    private static final String FILE_2 = "/file/to/rename/2";
    private static final String SUFFIX = ".txt";
    private final RenamingConfigs configs = new RenamingConfigs();

    @Before
    public void setUp() throws Exception {
        configs.setSuffix(SUFFIX);
        List<Path> filesToRename = new ArrayList<>();
        filesToRename.add(Paths.get(FILE_1));
        filesToRename.add(Paths.get(FILE_2));
        configs.setFiles(filesToRename);
        String testConfigContent = new ObjectMapper().writeValueAsString(configs);
        Path path = Paths.get(TEST_RESOURCES_PATH + TEST_CONFIG_JSON);
        Files.writeString(path, testConfigContent, StandardOpenOption.WRITE);
    }

    @Test
    public void getConfigsTest() {
        JsonGetter jsonGetter = new JsonGetter();
        RenamingConfigs configsFromFile =
                jsonGetter.getConfigs(TEST_RESOURCES_PATH + TEST_CONFIG_JSON);
        Assert.assertEquals(configs.getSuffix(), configsFromFile.getSuffix());
        Assert.assertEquals(configs.getFiles(), configsFromFile.getFiles());
    }

    @Test(expected = RuntimeException.class)
    public void getConfigsFromNotExistingFile() {
        String configFilePath = TEST_RESOURCES_PATH + "NoSuchFile";
        new JsonGetter().getConfigs(configFilePath);
    }
}
