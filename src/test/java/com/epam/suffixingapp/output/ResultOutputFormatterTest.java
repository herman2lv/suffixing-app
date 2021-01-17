package com.epam.suffixingapp.output;

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

import static com.epam.suffixingapp.messages.UserMessages.RESULT_OUTPUT_FORMAT;

public class ResultOutputFormatterTest {
    private final static String CONFIG_PATH = "/config/File/Path";
    private static final String FILE_TO_RENAME = "fileToRename";
    private static final String RENAMED_FILE = FILE_TO_RENAME + ".txt";
    private final List<RenamingResult> files = new ArrayList<>();

    @Before
    public void setUp() {
        files.add(new RenamingResult(Paths.get(FILE_TO_RENAME), Paths.get(RENAMED_FILE)));
    }

    @Test
    public void formatResultOfRenamingToJsonStringTest() throws JsonProcessingException {
        String output =
                ResultOutputFormatter.formatResultOfRenamingToJsonString(files, CONFIG_PATH);
        JsonNode jsonNode = new ObjectMapper().readTree(output);
        Assert.assertEquals(new ObjectMapper().writeValueAsString(CONFIG_PATH),
                jsonNode.get("configFilePath").toString());
        Assert.assertEquals(new ObjectMapper().writeValueAsString(files),
                jsonNode.get("renamedFiles").toString());
    }

    @Test
    public void formatResultOfRenamingToXMLStringTest() {

    }

    @Test
    public void saveResultOfRenamingToStringTest() {
        String outputToTest = ResultOutputFormatter.saveResultOfRenamingToString(files);
        String correctOutput = String.format(RESULT_OUTPUT_FORMAT,
                files.get(0).getOldPath().getFileName(),
                files.get(0).getNewPath().getFileName());
        Assert.assertEquals(correctOutput, outputToTest);
    }
}
