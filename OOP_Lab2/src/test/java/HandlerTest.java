import data.Gem;
import data.Tags;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import parsers.Handler;

import java.util.HashMap;
import java.util.Map;

public class HandlerTest {

        @Test
        public void testSetField() throws SAXException {
            Handler handler = new Handler();
            Map<String, String> attributes = new HashMap<>();

            handler.startElement("", "", "Gem", null);
            handler.startElement("", "", "visualParameters", null);
            handler.setField(Tags.NAME, "Diamond",attributes);
            handler.setField(Tags.ID, "1", attributes);
            handler.setField(Tags.ORIGIN, "South Africa" , attributes);
            handler.setField(Tags.PRECIOUSNESS, "precious", attributes);
            handler.setField(Tags.VALUE, "3.0", attributes);
            handler.setField(Tags.VISUAL_PARAMETERS, "", attributes);
            handler.setField(Tags.COLOR, "colorless", attributes);
            handler.setField(Tags.TRANSPARENCY, "0.9",attributes);
            handler.setField(Tags.NUMBER_OF_FACES, "57", attributes);
            handler.endElement("", "", "Gem");

            Assertions.assertEquals(1, handler.getGemList().size());

            Gem gem = handler.getGemList().get(0);

            Assertions.assertEquals("Diamond", gem.getName());
            Assertions.assertEquals(1, gem.getId());
            Assertions.assertEquals("South Africa", gem.getOrigin());
            Assertions.assertEquals("precious", gem.getPreciousness());
            Assertions.assertEquals(3.0, gem.getValue(), 0.001);
            Assertions.assertEquals("colorless", gem.getVisualParameters().getColor());
            Assertions.assertEquals(0.9, gem.getVisualParameters().getTransparency(), 0.001);
            Assertions.assertEquals(57, gem.getVisualParameters().getNumberOfFaces());
        }
    }
