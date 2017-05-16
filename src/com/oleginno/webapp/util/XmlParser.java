package com.oleginno.webapp.util;

import com.oleginno.webapp.WebAppException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.Writer;


/**
 * Oleh Savych
 * 14.05.17
 */

public class XmlParser {

    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    public XmlParser(Class... classesToBeBound) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(classesToBeBound);

            marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            //marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

            unmarshaller = ctx.createUnmarshaller();
        } catch (JAXBException e) {
            throw new WebAppException("Jaxb init failed", e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T unmarshall(Reader reader)  {
        try {
            return (T) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new WebAppException("Jaxb unmarshall failed", e);
        }
    }

    public void marshall(Object instance, Writer writer) {
        try {
            marshaller.marshal(instance, writer);
        } catch (JAXBException e) {
            throw new WebAppException("Jaxb marshal failed", e);
        }
    }
}
