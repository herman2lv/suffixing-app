package com.epam.suffixingapp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.epam.suffixingapp.UserMessages.RESULT_OUTPUT_FORMAT;

public class ResultOutputPrinterTest {
    private final ByteArrayOutputStream standardOutputCaptor = new ByteArrayOutputStream();
    private static final String FILE_TO_RENAME = "fileToRename";
    private static final String RENAMED_FILE = FILE_TO_RENAME + ".txt";

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(standardOutputCaptor));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(System.out);
    }

    @Test
    public void printResultToStandardOutput() {
        RenamingResult renamingResult =
                new RenamingResult(Paths.get(FILE_TO_RENAME), Paths.get(RENAMED_FILE));
        List<RenamingResult> resultList = new ArrayList<>();
        resultList.add(renamingResult);
        ResultOutputPrinter.printResultOfRenamingToStdout(resultList);
        String output = String.format(RESULT_OUTPUT_FORMAT,
                renamingResult.getOldPath().getFileName(),
                renamingResult.getNewPath().getFileName());
        Assert.assertEquals(output, standardOutputCaptor.toString());
    }

    @Test(expected = RuntimeException.class)
    public void printEmptyResultToStandardOutput() {
        ResultOutputPrinter.printResultOfRenamingToStdout(new ArrayList<>());
    }
}
