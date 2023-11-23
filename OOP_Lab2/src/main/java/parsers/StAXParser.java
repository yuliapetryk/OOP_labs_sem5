package parsers;

import data.Gem;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Logger;

public class StAXParser {
    public List<Gem> runStAXParser(File file) throws XMLStreamException, FileNotFoundException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        xmlInputFactory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
        xmlInputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        Handler handler = new Handler();

            XMLEventReader eventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(file));
            while (eventReader.hasNext()) {
                XMLEvent nextEvent = eventReader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();

                    nextEvent = eventReader.nextEvent();
                    String name = startElement.getName().getLocalPart();

                    List<Attribute> attributes = new ArrayList<>();
                    Iterator<Attribute> iterator = startElement.getAttributes();
                    while (iterator.hasNext()) {
                        attributes.add(iterator.next());
                    }

                    Map<String, String> attributeMap = new HashMap<>();

                    for (Attribute attribute : attributes) {
                        attributeMap.put(attribute.getName().getLocalPart(), attribute.getValue());
                    }

                    if (nextEvent.isCharacters()) {
                        handler.setField(name, nextEvent.asCharacters().getData(), attributeMap);
                    } else if (!nextEvent.isStartDocument() && !nextEvent.isEndDocument()) {
                        handler.setField(name, null, attributeMap);
                    }
                }
            }
            return Handler.getGemList();
    }
}
