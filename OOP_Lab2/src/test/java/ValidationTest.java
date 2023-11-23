
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import parsers.Validation;

import static org.junit.jupiter.api.Assertions.*;
public class ValidationTest {
    @Test
    public void validateTest() throws SAXException {
        assertTrue(Validation.validate("src/main/resources/gem.xml", "src/main/resources/gem.xsd"));
    }
}
