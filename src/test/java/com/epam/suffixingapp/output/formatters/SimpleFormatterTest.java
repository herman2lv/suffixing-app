package com.epam.suffixingapp.output.formatters;

import com.epam.suffixingapp.beans.RenamingResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.epam.suffixingapp.messages.UserMessages.CONFIG_FILE_PATH_FORMATTED;
import static com.epam.suffixingapp.messages.UserMessages.RESULT_OUTPUT_FORMAT;

public class SimpleFormatterTest {
    private final static String CONFIG_PATH = "/config/File/Path";
    private static final String FILE_TO_RENAME = "fileToRename";
    private static final String RENAMED_FILE = FILE_TO_RENAME + ".txt";
    private final List<RenamingResult> files = new ArrayList<>();

    @Before
    public void setUp() {
        files.add(new RenamingResult(Paths.get(FILE_TO_RENAME), Paths.get(RENAMED_FILE)));
    }

    @Test
    public void formatTest() {
        String outputToTest = new SimpleFormatter().format(files, CONFIG_PATH);
        String correctOutput = String.format(CONFIG_FILE_PATH_FORMATTED, CONFIG_PATH)
                               + String.format(RESULT_OUTPUT_FORMAT,
                files.get(0).getOldPath().getFileName(),
                files.get(0).getNewPath().getFileName());
        Assert.assertEquals(correctOutput, outputToTest);
    }
}
