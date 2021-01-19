package com.epam.suffixingapp.output.formatters;

import com.epam.suffixingapp.enums.OutputFileType;
import org.junit.Assert;
import org.junit.Test;

public class ResultOutputFormatterFactoryTest {
    @Test
    public void createJsonFormatterTest() {
        ResultOutputFormatter formatter =
                ResultOutputFormatterFactory.createFormatter(OutputFileType.JSON);
        Assert.assertEquals(JsonFormatter.class, formatter.getClass());
    }

    @Test
    public void createXMLFormatterTest() {
        ResultOutputFormatter formatter =
                ResultOutputFormatterFactory.createFormatter(OutputFileType.XML);
        Assert.assertEquals(XMLFormatter.class, formatter.getClass());
    }

    @Test
    public void createSimpleFormatterTest() {
        ResultOutputFormatter formatter =
                ResultOutputFormatterFactory.createFormatter(OutputFileType.NONE);
        Assert.assertEquals(SimpleFormatter.class, formatter.getClass());
    }
}
