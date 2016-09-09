package com.savinykh.testtasks.calculator;

import com.savinykh.testtasks.calculator.validators.ValidatorXML;
import com.savinykh.testtasks.calculator.writer.AnswerWriter;
import com.savinykh.testtasks.calculator.writer.AnswerWriterXML;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ManUnited on 30.06.2016.
 * класс AnswerWriterXMLTest для проверки правильности записи результатов в xml-файл
 */
public class AnswerWriterXMLTest {

    /**
     *  метод testWriteAnswer() для проверки writeAnswer(List<Double> answers, String filePath)
     */
    @Test
    public void testWriteAnswer() {

        final String resultXML = "src/main/resources/FileWriteTest.xml";

        final String xsdFile = "src/main/resources/Calculator.xsd";

        AnswerWriter writer = new AnswerWriterXML();
        List<Double> answers = new ArrayList<>();
        answers.add(0.0);
        answers.add(3.0);
        writer.writeAnswer(answers, resultXML);
        ValidatorXML validatorXML = new ValidatorXML();
        boolean isValid = validatorXML.validateXML(xsdFile, resultXML);
        Assert.assertEquals("Выходной файл не валиден", true, isValid);
    }
}
