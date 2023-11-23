package parsers;

import data.Gem;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SAXParser {

    public List<Gem> runSAXParser(File file) throws SAXException, ParserConfigurationException, IOException {
        Handler handler = new Handler();

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser saxParser = saxParserFactory.newSAXParser();

        saxParser.parse(file, handler);
        return Handler.getGemList();
    }
}
