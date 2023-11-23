package parsers;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class Validation {
    private static DocumentBuilder db = null;


    public static boolean validate(String xmlFile, String xsdFile) throws SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        SchemaFactory sf =
                SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema s = sf.newSchema(new File(xsdFile));
        dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        dbf.setSchema(s);
            try {
                db = dbf.newDocumentBuilder();
                db.setErrorHandler(new SimpleErrorHandler());
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            Document doc = null;

            try {
                doc = db.parse(new File(xmlFile));
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;

    }
    public static class SimpleErrorHandler implements ErrorHandler {

        public void warning(SAXParseException e) throws SAXException {
            System.out.println("Row" + e.getLineNumber() + ":");
            System.out.println(e.getMessage());
        }


        public void error(SAXParseException e) throws SAXException {
            System.out.println("Row" + e.getLineNumber() + ":");
            System.out.println(e.getMessage());
        }

        public void fatalError(SAXParseException e) throws SAXException {
            System.out.println("Row" + e.getLineNumber() + ":");
            System.out.println(e.getMessage());
        }

    }
}
