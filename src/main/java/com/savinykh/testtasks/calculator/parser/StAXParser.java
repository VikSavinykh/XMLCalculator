package com.savinykh.testtasks.calculator.parser;

import com.savinykh.testtasks.calculator.domain.Operation;
import com.savinykh.testtasks.calculator.domain.OperatorType;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ManUnited on 30.06.2016.
 * Класс StAXParser для вычисления нужного выражения из входного xml-файла
 */
public class StAXParser {

    /**
     * метод parseDoc разбивает файл на составные части для составления выражения, которое надо вычислить
     * @param pathToFile - путь до xml-файла
     * @return - возвращает список объектов с результатами
     */
    public List<Operation> parseDoc(String pathToFile) {

        List<Operation> results = new ArrayList<>();
        try {
            XMLStreamReader reader = XMLInputFactory.newInstance()
                .createXMLStreamReader(new FileInputStream(pathToFile));
            reader.nextTag();
            while (!reader.getLocalName().equals("expressions")) {
                reader.nextTag();
            }
            reader.nextTag();
            while (!reader.isEndElement()
                || !reader.getLocalName().equals("expressions")) {
                results.add(getExpressionGeneralOperation(reader));
                reader.nextTag();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (XMLStreamException e) {
            System.out.println("XMLStreamException");
            e.printStackTrace();
        }
        return results;
    }

    /**
     * метод getExpressionGeneralOperation для вычисления главного выражения
     * @param reader - объект для обхода файла
     * @return - возвращает объект с результатом выражения
     * @throws XMLStreamException
     */
    private Operation getExpressionGeneralOperation(XMLStreamReader reader)
        throws XMLStreamException {
        Operation generalOperation;
        reader.nextTag();
        generalOperation = getOperation(reader);
        while (!reader.isEndElement() || !reader.getLocalName().equals("expression")) {
            reader.nextTag();
        }
        return generalOperation;
    }

    /**
     * метод getOperation составляет главное выражение
     * @param reader - объект для обхода файла
     * @return - возвращает объект с результатом выражения
     * @throws XMLStreamException
     */
    private Operation getOperation(XMLStreamReader reader) throws XMLStreamException {
        Operation operation = new Operation();
        if (reader.getLocalName().equals("arg")) {
            operation.setNumber(Double.parseDouble(reader.getElementText()));
            return operation;
        } else {
            String attributeValue = reader.getAttributeValue(0);
            operation.setOperatorType(OperatorType.valueOf(attributeValue));
            reader.nextTag();
            operation.setOperationOne(getOperation(reader));
            reader.nextTag();
            while (reader.isEndElement() && reader.getLocalName().equals("operation")) {
                reader.nextTag();
            }
            operation.setOperationTwo(getOperation(reader));
            return operation;
        }
    }

}
