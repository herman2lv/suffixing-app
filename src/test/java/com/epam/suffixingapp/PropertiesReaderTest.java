package com.epam.suffixingapp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

public class PropertiesReaderTest {
    private PropertiesReader prCorrect;
    private PropertiesReader prNotCorrect;
    private static final String PROPERTY_FILES = "files";
    private static final String PROPERTY_SUFFIX = "suffix";
    private static final String FILES_PATHS_SPLIT_REGEX = ":";
    private static final String FILE1 = "/dir1/dir2/file1";
    private static final String FILE2 = "/dir1/dir3/file2";
    private static final String TEST_SUFFIX = ".txt";

    @Before
    public void setUp() {
        Properties propertiesCorrect = new Properties();
        propertiesCorrect.put(PROPERTY_FILES, FILE1 + FILES_PATHS_SPLIT_REGEX + FILE2);
        propertiesCorrect.put(PROPERTY_SUFFIX, TEST_SUFFIX);
        prCorrect = new PropertiesReader(propertiesCorrect);
        Properties propertiesNotCorrect = new Properties();
        propertiesNotCorrect.put("uselessKey", "uselessData");
        propertiesNotCorrect.put("suffixWithMistyping", ".suf");
        prNotCorrect = new PropertiesReader(propertiesNotCorrect);

    }

    @Test
    public void getFilesCorrect() {
        List<Path> files = prCorrect.getFiles();
        Assert.assertEquals(FILE2, files.get(1).toString());
    }

    @Test(expected = RuntimeException.class)
    public void getFilesNotCorrect() {
        prNotCorrect.getFiles();

    }

    @Test
    public void getSuffixCorrect() {
        Assert.assertEquals(TEST_SUFFIX, prCorrect.getSuffix());
    }

    @Test(expected = RuntimeException.class)
    public void getSuffixNotCorrect() {
        prNotCorrect.getSuffix();
    }
}
