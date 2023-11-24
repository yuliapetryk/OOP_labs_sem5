package parsers;

import data.Gem;
import data.Tags;
import data.VisualParameters;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Handler extends DefaultHandler {

    private Gem currentGem;
    private VisualParameters currentVisualParameters;
    private StringBuilder currentText;
    private static final List<Gem> gemList = new ArrayList<>();

    public static List<Gem> getGemList() {
        return gemList;
    }

    public static String getRoot() {
        return "Gem";
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentText = new StringBuilder();

        if ("Gem".equals(qName)) {
            currentGem = new Gem();
        } else if ("visualParameters".equals(qName)) {
            currentVisualParameters = new VisualParameters();
            currentGem.setVisualParameters(currentVisualParameters);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentText.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String text = currentText.toString().trim();

        if ("name".equals(qName)) {
            currentGem.setName(text);
        } else if ("id".equals(qName)) {
            currentGem.setId(Integer.parseInt(text));
        } else if ("preciousness".equals(qName)) {
            currentGem.setPreciousness(text);
        } else if ("origin".equals(qName)) {
            currentGem.setOrigin(text);
        } else if ("value".equals(qName)) {
            currentGem.setValue(Double.parseDouble(text));
        } else if ("color".equals(qName)) {
            currentVisualParameters.setColor(text);
        } else if ("transparency".equals(qName)) {
            currentVisualParameters.setTransparency(Double.parseDouble(text));
        } else if ("numberOfFaces".equals(qName)) {
            currentVisualParameters.setNumberOfFaces(Integer.parseInt(text));
        } else if ("visualParameters".equals(qName)) {
            currentGem.setVisualParameters(currentVisualParameters);
        } else if ("Gem".equals(qName)) {
            gemList.add(currentGem);
            System.out.println(currentGem);
        }
    }

    public void setField(String name, String str, Map<String, String> attributes) {

        switch(name) {
            case Tags.GEM :
                currentGem = new Gem();
                gemList.add(currentGem);
                break;
            case Tags.NAME :
                currentGem.setName(str);
                break;
            case Tags.ID :
                currentGem.setId(Integer.valueOf(str));
                break;
            case Tags.ORIGIN :
                currentGem.setOrigin(str);
                break;
            case Tags.PRECIOUSNESS:
                currentGem.setPreciousness(str);
                break;
            case Tags.VALUE:
                currentGem.setValue(Double.parseDouble(str));
                break;
            case Tags.COLOR:
                currentGem.getVisualParameters().setColor(str);
                break;
            case Tags.TRANSPARENCY :
                currentGem.getVisualParameters().setTransparency(Double.parseDouble(str));
                break;
            case Tags.NUMBER_OF_FACES:
                currentGem.getVisualParameters().setNumberOfFaces(Integer.parseInt(str));
                break;
            case Tags.VISUAL_PARAMETERS :
                VisualParameters visualParameters = new VisualParameters();
                currentGem.setVisualParameters(visualParameters);
                break;
            }
        }
}


