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

public class ResultOutputPrinterTest {
    private final ByteArrayOutputStream standardOutputCaptor = new ByteArrayOutputStream();
    private static final String RESULT_OUTPUT = "output data";
    private static final String FILE_TO_SAVE_RESULTS = "output.txt";

    @Before
    public void setUp() {
        System.setOut(new PrintStream(standardOutputCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(System.out);
    }

    @Test
    public void printResultToStandardOutputTest() {
        ResultOutputPrinter.printResultOfRenamingToStdout(RESULT_OUTPUT);
        Assert.assertEquals(RESULT_OUTPUT + '\n', standardOutputCaptor.toString());
    }

    @Test
    public void printEmptyResultToStandardOutputTest() {
        ResultOutputPrinter.printResultOfRenamingToStdout("");
        Assert.assertEquals("\n", standardOutputCaptor.toString());
    }

    @Test
    public void saveResultOfRenamingToFileTest() throws IOException {
        Path outputFile = Paths.get(TEST_RESOURCES_PATH + FILE_TO_SAVE_RESULTS);
        ResultOutputPrinter.saveResultOfRenamingToFile(outputFile, RESULT_OUTPUT);
        Assert.assertEquals(RESULT_OUTPUT, Files.readString(outputFile));
    }

    @Test
    public void saveResultOfRenamingToFileInNotExistingDirTest() {
        Path outputFile = Paths.get(TEST_RESOURCES_PATH + "noSuchDir/" + FILE_TO_SAVE_RESULTS);
        ResultOutputPrinter.saveResultOfRenamingToFile(outputFile, RESULT_OUTPUT);
    }
}
