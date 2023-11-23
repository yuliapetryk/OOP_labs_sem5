package parsers;

import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class Validation {
    public static boolean validate(String xmlFile, String xsdFile) throws SAXException {
        try{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        SchemaFactory sf =
                SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema s = sf.newSchema(new File(xsdFile));
        dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        dbf.setSchema(s);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
