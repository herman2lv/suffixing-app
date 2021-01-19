package com.epam.suffixingapp.configgetters;

import com.epam.suffixingapp.beans.RenamingConfigs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class XmlGetter implements ConfigGetter {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlGetter.class);

    @Override
    public RenamingConfigs getConfigs(String path) {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        try {
            XMLEventReader reader = factory.createXMLEventReader(new FileReader(path));
            RenamingConfigs configs = new RenamingConfigs();
            List<Path> files = new ArrayList<>();
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    StartElement se = event.asStartElement();
                    switch (se.getName().getLocalPart()) {
                        case "suffix" -> configs.setSuffix(reader.getElementText());
                        case "output" -> configs.setOutput(reader.getElementText());
                        case "file" -> files.add(Paths.get(reader.getElementText()));
                        default -> { }
                    }
                }
            }
            configs.setFiles(files);
            return configs;
        } catch (XMLStreamException | FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
