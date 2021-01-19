package com.epam.suffixingapp.output.formatters;

import com.epam.suffixingapp.beans.RenamingResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.epam.suffixingapp.output.formatters.XMLFormatter.CONFIG_FILE_PATH;
import static com.epam.suffixingapp.output.formatters.XMLFormatter.RENAMED_FILES;

public class XMLFormatterTest {private final static String CONFIG_PATH = "/config/File/Path";
    private static final String FILE_TO_RENAME = "fileToRename";
    private static final String RENAMED_FILE = FILE_TO_RENAME + ".txt";
    private final List<RenamingResult> files = new ArrayList<>();

    @Before
    public void setUp() {
        files.add(new RenamingResult(Paths.get(FILE_TO_RENAME), Paths.get(RENAMED_FILE)));
    }

    @Test
    public void formatTest() throws IOException, SAXException, ParserConfigurationException {
        String output = new XMLFormatter().format(files, CONFIG_PATH);
        Assert.assertEquals(CONFIG_PATH, getConfigPath(output));
        Assert.assertEquals(FILE_TO_RENAME + RENAMED_FILE, getRenamedFiles(output));
    }

    private String getConfigPath(String xmlString)
            throws ParserConfigurationException, IOException, SAXException {
        Document document = getDOM(xmlString);
        NodeList list = document.getElementsByTagName(CONFIG_FILE_PATH);
        return list.item(0).getTextContent();
    }

    private String getRenamedFiles(String xmlString)
            throws ParserConfigurationException, IOException, SAXException {
        Document document = getDOM(xmlString);
        NodeList list = document.getElementsByTagName(RENAMED_FILES);
        return list.item(0).getTextContent();
    }

    private Document getDOM(String xmlString)
            throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        return builder.parse(new InputSource(new StringReader(xmlString)));
    }
}
