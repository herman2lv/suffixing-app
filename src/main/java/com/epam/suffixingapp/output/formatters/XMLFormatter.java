package com.epam.suffixingapp.output.formatters;

import com.epam.suffixingapp.beans.RenamingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.epam.suffixingapp.messages.UserMessages.LOG_XML_FORMATTING_FAILED;

public class XMLFormatter implements ResultOutputFormatter {
    private static final Logger LOGGER = LoggerFactory.getLogger(XMLFormatter.class);
    public static final String RESULT_OUTPUT = "resultOutput";
    public static final String CONFIG_FILE_PATH = "configFilePath";
    public static final String TIME_OF_OPERATION = "timeOfOperation";
    public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
    public static final String RENAMED_FILES = "renamedFiles";
    public static final String FILE = "file";
    public static final String OLD_NAME = "oldName";
    public static final String NEW_NAME = "newName";

    @Override
    public String format(List<RenamingResult> files, String configFilePath) {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        StringWriter sw = new StringWriter();
        try {
            XMLStreamWriter writer = factory.createXMLStreamWriter(sw);
            writer.writeStartDocument();
            writer.writeStartElement(RESULT_OUTPUT);
            addDataElement(writer, CONFIG_FILE_PATH, configFilePath);
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            addDataElement(writer, TIME_OF_OPERATION, df.format(new Date()));
            addListOfRenamedFiles(writer, files);
            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();
            writer.close();
            return sw.toString();
        } catch (XMLStreamException | NullPointerException e) {
            LOGGER.error(LOG_XML_FORMATTING_FAILED, e);
            return LOG_XML_FORMATTING_FAILED;
        }
    }

    private void addDataElement(XMLStreamWriter writer, String elementName, String content)
            throws XMLStreamException {
        writer.writeStartElement(elementName);
        writer.writeCharacters(content);
        writer.writeEndElement();
    }

    private void addListOfRenamedFiles(XMLStreamWriter writer, List<RenamingResult> files)
            throws XMLStreamException {
        writer.writeStartElement(RENAMED_FILES);
        for (RenamingResult file: files) {
            writer.writeStartElement(FILE);
            addDataElement(writer, OLD_NAME, file.getOldPath().toString());
            addDataElement(writer, NEW_NAME, file.getNewPath().toString());
            writer.writeEndElement();
        }
        writer.writeEndElement();
    }


}
