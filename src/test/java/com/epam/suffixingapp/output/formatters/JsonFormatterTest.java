package com.epam.suffixingapp.output.formatters;

import com.epam.suffixingapp.beans.RenamingResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonFormatterTest {
    private final static String CONFIG_PATH = "/config/File/Path";
    private static final String FILE_TO_RENAME = "fileToRename";
    private static final String RENAMED_FILE = FILE_TO_RENAME + ".txt";
    public static final String CONFIG_FILE_PATH_TAG = "configFilePath";
    public static final String RENAMED_FILES_TAG = "renamedFiles";
    private final List<RenamingResult> files = new ArrayList<>();

    @Before
    public void setUp() {
        files.add(new RenamingResult(Paths.get(FILE_TO_RENAME), Paths.get(RENAMED_FILE)));
    }

    @Test
    public void formatTest() throws JsonProcessingException {
        String output = new JsonFormatter().format(files, CONFIG_PATH);
        JsonNode jsonNode = new ObjectMapper().readTree(output);
        Assert.assertEquals(new ObjectMapper().writeValueAsString(CONFIG_PATH),
                jsonNode.get(CONFIG_FILE_PATH_TAG).toString());
        Assert.assertEquals(new ObjectMapper().writeValueAsString(files),
                jsonNode.get(RENAMED_FILES_TAG).toString());
    }
}
