package parsers;

import data.Gem;
import data.VisualParameters;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


public class Handler extends DefaultHandler {

    private Gem currentGem;
    private VisualParameters currentVisualParameters;
    private StringBuilder currentText;
    private List<Gem> gemList = new ArrayList<>();

    public static List<Gem> getGemList(){
        return gemList;
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentText = new StringBuilder();

        if ("Gem".equals(qName)) {
            currentGem = new Gem();
        } else if ("visualParameters".equals(qName)) {
            currentVisualParameters = new VisualParameters();
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
}


