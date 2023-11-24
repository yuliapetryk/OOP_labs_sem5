package parsers;

import data.Gem;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DOMParser {
    public List<Gem> runDOMParser(File file) throws SAXException, ParserConfigurationException, IOException {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(file);
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();
            Handler handler = new Handler();
            NodeList gemNodes = root.getElementsByTagName(handler.getRoot());

            for (int i = 0; i < gemNodes.getLength(); i++) {
                    Node gemNode = gemNodes.item(i);
                    Element gemElement = (Element) gemNode;
                    parseGemElements(gemElement, handler);

            }
            return handler.getGemList();

    }

    private static void parseGemElements(Node node,  Handler handler ) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Map<String, String> attributes = new HashMap<>();
            if (node.getAttributes() != null) {
                NamedNodeMap attributeMap = node.getAttributes();
                for (int i = 0; i < attributeMap.getLength(); i++) {
                    Node attributeNode = attributeMap.item(i);
                    attributes.put(attributeNode.getNodeName(), attributeNode.getTextContent());
                }
            }
            handler.setField(node.getNodeName(), node.getTextContent(), attributes);
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                parseGemElements(node.getChildNodes().item(i), handler);
            }
        }
    }
}
