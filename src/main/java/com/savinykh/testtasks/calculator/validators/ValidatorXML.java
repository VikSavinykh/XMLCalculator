package com.savinykh.testtasks.calculator.validators;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * Created by ManUnited on 30.06.2016.
 * класс ValidatorXML для проверки xml-файла по схеме xsd-файла
 */
public class ValidatorXML {

    /**
     * метод validateXML для проверки xml-файла по схеме xsd-файла
     * @param xsdPath - путь до xsd-файла
     * @param xmlPath - путь до xml-файла
     * @return - возвращает ответ на проверку файла
     */
    public boolean validateXML(String xsdPath, String xmlPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        }
        catch (SAXException e) {
            return false;
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }
}
