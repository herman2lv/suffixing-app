package com.epam.suffixingapp.output;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.epam.suffixingapp.TestPath.TEST_RESOURCES_PATH;

public class ResultOutputSaverTest {
    public static final int EXCEPTION_MESSAGE_END_INDEX = 33;
    private static final String RESULT_OUTPUT = "output data";
    private static final String FILE_TO_SAVE_RESULTS = "output.txt";
    public static final String EXCEPTION_REGEX = ".*NoSuchFileException.*";
    private final ByteArrayOutputStream standardOutputCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setErr(new PrintStream(standardOutputCaptor));
    }

    @After
    public void tearDown() {
        System.setErr(System.err);
    }

    @Test
    public void saveToFileTest() throws IOException {
        Path outputFile = Paths.get(TEST_RESOURCES_PATH + FILE_TO_SAVE_RESULTS);
        ResultOutputSaver.saveToFile(outputFile, RESULT_OUTPUT);
        Assert.assertEquals(RESULT_OUTPUT, Files.readString(outputFile));
    }

    @Test
    public void saveResultOfRenamingToFileInNotExistingDirTest() {
        Path outputFile = Paths.get(TEST_RESOURCES_PATH + "noSuchDir/" + FILE_TO_SAVE_RESULTS);
        ResultOutputSaver.saveToFile(outputFile, RESULT_OUTPUT);
        Assert.assertTrue(standardOutputCaptor.toString()
                .substring(0, EXCEPTION_MESSAGE_END_INDEX)
                .matches(EXCEPTION_REGEX));
    }
}

