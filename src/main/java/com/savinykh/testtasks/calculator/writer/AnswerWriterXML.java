package com.savinykh.testtasks.calculator.writer;

import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by ManUnited on 30.06.2016.
 * класс AnswerWriterXML для записи результатов работы в xml-файл
 */
public class AnswerWriterXML implements AnswerWriter {

    /**
     * метод writeAnswer для записи результатов работы в xml-файл
     * @param answers - список с результатами
     * @param filePath - путь до файла, в который производить запись
     */
    @Override
    public void writeAnswer(List<Double> answers, String filePath) {
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
        try {
            XMLStreamWriter writer = new IndentingXMLStreamWriter(
                xmlOutputFactory.createXMLStreamWriter(new FileOutputStream(filePath)));
            writer.writeStartDocument("UTF-8", "1.0");
            // <simpleCalculator >
            writer.writeStartElement("simpleCalculator");
            // <expressionResults>
            writer.writeStartElement("expressionResults");

            for (Double answer : answers) {
                // <expressionResult>
                writer.writeStartElement("expressionResult");
                // <result>
                writer.writeStartElement("result");
                writer.writeCharacters(String.valueOf(answer));
                // </result>
                writer.writeEndElement();
                // </expressionResult>
                writer.writeEndElement();
            }

            // </expressionResults>
            writer.writeEndElement();
            // </simpleCalculator>
            writer.writeEndElement();
            writer.flush();
            writer.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
