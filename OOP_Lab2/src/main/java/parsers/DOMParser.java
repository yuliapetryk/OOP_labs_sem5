package parsers;

import data.Gem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(file);

            Element root = document.getDocumentElement();
            Handler handler = new Handler();
            NodeList gemNodes = root.getElementsByTagName(handler.getRoot());

            for (int i = 0; i < gemNodes.getLength(); i++) {
                Node gemNode = gemNodes.item(i);
                if (gemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element gemElement = (Element) gemNode;
                    parseGemElements(gemElement, handler);
                }
            }

            return Handler.getGemList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void parseGemElements(Node node,  Handler handler ) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Map<String, String> attributes = new HashMap<>();
            if (node.getAttributes() != null) {
                for (int i = 0; i < node.getAttributes().getLength(); i++) {
                    attributes.put(node.getAttributes().item(i).getNodeName(),
                            node.getAttributes().item(i).getTextContent());
                }
            }
            handler.setField(node.getNodeName(), node.getTextContent());
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                parseGemElements(node.getChildNodes().item(i), handler);
            }
        }
    }
}
