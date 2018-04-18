package com.epam.cdp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.Unmarshaller;

import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.IOException;

public class XMLConverter {

    private Logger log = LoggerFactory.getLogger(XMLConverter.class);

    private Unmarshaller unmarshaller;

    public Unmarshaller getUnMarshaller() {
        return unmarshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public Object convertFromXMLToObject(String xmlFile) {
        try (FileInputStream is = new FileInputStream(xmlFile)) {
            return getUnMarshaller().unmarshal(new StreamSource(is));
        } catch (IOException e) {
            log.error("Cannot read " + xmlFile);
        }
        return null;
    }

}
