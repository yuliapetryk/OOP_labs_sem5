import data.Gem;
import data.VisualParameters;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parsers.SAXParser;

public class SAXParserTest {

    private SAXParser parser;

    @Test
    public void testRunSAXParser() throws SAXException, ParserConfigurationException, IOException {

        File file = new File("src/main/resources/gem.xml");
        List<Gem> expectedGemList = Arrays.asList(
                new Gem("Diamond", 1, "precious", "South Africa", 3.0,
                        new VisualParameters("colorless", 0.9, 57)),
                new Gem("Amethyst", 2, "semi-precious", "Brazil", 8.0,
                        new VisualParameters("purple", 0.7, 33)),
                new Gem("Citrine", 3, "semi-precious", "Brazil", 1.2,
                        new VisualParameters("yellow", 0.85, 38)),
                new Gem("Ruby", 4, "precious", "Myanmar", 2.5,
                        new VisualParameters("red", 0.95, 50))
        );

        parser= new SAXParser();
        List<Gem> actualGemList = parser.runSAXParser(file);

        Assertions.assertEquals(expectedGemList.toString(), actualGemList.toString());
    }
}
