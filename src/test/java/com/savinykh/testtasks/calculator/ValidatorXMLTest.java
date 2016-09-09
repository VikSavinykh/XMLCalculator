package com.savinykh.testtasks.calculator;

import com.savinykh.testtasks.calculator.validators.ValidatorXML;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ManUnited on 02.07.2016.
 * класс ValidatorXMLTest для тестирования валидатора
 */

public class ValidatorXMLTest {

    /**
     * Тест для метода validateXML(String xsdPath, String xmlPath)
     */

    @Test
    public void validateXMLTest () {

        final String fileTrue = "src/main/resources/sampleTest.xml";
        final String fileXSD = "src/main/resources/Calculator.xsd";
        final String fileFalse = "src/main/resources/ValidatorFailedTest.xml";

        ValidatorXML validatorXML = new ValidatorXML();
        boolean resultTrue = validatorXML.validateXML(fileXSD, fileTrue);
        Assert.assertEquals("Файл не соответствует", true, resultTrue);
        boolean resultFalse = validatorXML.validateXML(fileXSD, fileFalse);
        Assert.assertEquals("Файл не соответствует", false, resultFalse);
    }
}
