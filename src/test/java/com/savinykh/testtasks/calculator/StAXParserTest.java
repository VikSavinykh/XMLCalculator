package com.savinykh.testtasks.calculator;

import com.savinykh.testtasks.calculator.calculator.Calculator;
import com.savinykh.testtasks.calculator.domain.Operation;
import com.savinykh.testtasks.calculator.domain.OperatorType;
import com.savinykh.testtasks.calculator.parser.StAXParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Created by ManUnited on 02.07.2016.
 * класс StAXParserTest для тестирования правильности считывания с входного xml-файла
 */
public class StAXParserTest {

    /**
     * Тест для метода parseDoc(String pathToFile)
     * сравнивает объекты и возвращает правильность работы парсера
     */
    @Test
    public void parseDocTest() {
        String fileName = "src/main/resources/sampleTest.xml";
        StAXParser parser = new StAXParser();
        List<Operation> parseOperation = parser.parseDoc(fileName);
        List<Operation> handleOperations = prepareOperations();


        boolean sameOperations = parseOperation.size() == handleOperations.size();
        if(sameOperations){
            for (int i = 0; i < parseOperation.size(); i++) {
                sameOperations = compareOperations(parseOperation.get(i), handleOperations.get(i));
                if(!sameOperations){
                    break;
                }
            }
        }
        Assert.assertEquals("Парсер работает неверно", true, sameOperations);
    }

    /**
     * метод сравнивает два объекта
     * @param operation1 - 1 объект с операциями из входного файла
     * @param operation2 - 2 объект с операциями, сделанный вручную
     * @return - результат сравнения
     */
    private boolean compareOperations(Operation operation1, Operation operation2) {
        if (operation1 == null) {
            return operation2 == null;
        }
        if (operation2 == null) {
            return operation1 == null;
        }
        boolean sameOperatorType = operation1.getOperatorType() == operation2
                .getOperatorType();
        boolean sameNumber = Objects.equals(operation1.getNumber(),
                operation2.getNumber());
        boolean sameOperatorOne = compareOperations(operation1.getOperationOne(),
                operation2.getOperationOne());
        boolean sameOperatorTwo = compareOperations(operation1.getOperationTwo(),
                operation2.getOperationTwo());
        return sameNumber && sameOperatorType && sameOperatorOne && sameOperatorTwo;
    }

    /**
     * метод вычисляет результаты выражения из сгенерированного вручную выражения
     * @return возвращает список результатов
     */
    private List<Operation> prepareOperations() {

        List<Operation> list = new ArrayList<>();

        // 1 EXPRESSION

        // задаем 2
        Operation operationNum2 = new Operation();
        operationNum2.setNumber(2.0);
        // задаем 4
        Operation operationNum4 = new Operation();
        operationNum4.setNumber(4.0);
        // задаем SUM
        Operation operation1SUM = new Operation();
        operation1SUM.setOperationOne(operationNum2);
        operation1SUM.setOperationTwo(operationNum4);
        operation1SUM.setOperatorType(OperatorType.SUM);
        // задаем 1
        Operation operationNum1 = new Operation();
        operationNum1.setNumber(1.0);
        // задаем 4
        Operation operationNumb4 = new Operation();
        operationNumb4.setNumber(4.0);
        // задаем DIV
        Operation operation1DIV = new Operation();
        operation1DIV.setOperationOne(operationNum1);
        operation1DIV.setOperationTwo(operationNumb4);
        operation1DIV.setOperatorType(OperatorType.DIV);
        // задаем SUB
        Operation operation1SUB = new Operation();
        operation1SUB.setOperationOne(operation1SUM);
        operation1SUB.setOperationTwo(operation1DIV);
        operation1SUB.setOperatorType(OperatorType.SUB);
        // ------------------------------------------------------------
        //задаем 12
        Operation operationNum12 = new Operation();
        operationNum12.setNumber(12.0);
        // задаем 55
        Operation operationNum55 = new Operation();
        operationNum55.setNumber(55.0);
        // задаем SUM
        Operation operation2SUM = new Operation();
        operation2SUM.setOperationOne(operationNum12);
        operation2SUM.setOperationTwo(operationNum55);
        operation2SUM.setOperatorType(OperatorType.SUM);
        //задаем 123
        Operation operationNum123 = new Operation();
        operationNum123.setNumber(123.0);
        //задаем 4
        Operation operationNumbb4 = new Operation();
        operationNumbb4.setNumber(4.0);
        //задаем MUL
        Operation operation1MUL = new Operation();
        operation1MUL.setOperationOne(operationNum123);
        operation1MUL.setOperationTwo(operationNumbb4);
        operation1MUL.setOperatorType(OperatorType.MUL);
        //задаем SUB
        Operation operation2SUB = new Operation();
        operation2SUB.setOperationOne(operation2SUM);
        operation2SUB.setOperationTwo(operation1MUL);
        operation2SUB.setOperatorType(OperatorType.SUB);
        // задаем окончательный оператор MUL
        Operation operationGeneralMUL = new Operation();
        operationGeneralMUL.setOperationOne(operation1SUB);
        operationGeneralMUL.setOperationTwo(operation2SUB);
        operationGeneralMUL.setOperatorType(OperatorType.MUL);

        list.add(operationGeneralMUL);

        // 2 EXPRESSION
        Calculator calculator3 = new Calculator();
        // задаем 211
        Operation operationNum211 = new Operation();
        operationNum211.setNumber(211.0);
        // задаем 114
        Operation operationNum114 = new Operation();
        operationNum114.setNumber(114.0);
        // задаем DIV
        Operation operation2DIV = new Operation();
        operation2DIV.setOperationOne(operationNum211);
        operation2DIV.setOperationTwo(operationNum114);
        operation2DIV.setOperatorType(OperatorType.DIV);

        // задаем 13
        Operation operationNum13 = new Operation();
        operationNum13.setNumber(13.0);
        // задаем 41
        Operation operationNumb41 = new Operation();
        operationNumb41.setNumber(41.0);
        // задаем DIV
        Operation operation3DIV = new Operation();
        operation3DIV.setOperationOne(operationNum13);
        operation3DIV.setOperationTwo(operationNumb41);
        operation3DIV.setOperatorType(OperatorType.DIV);

        // задаем MUL
        Operation operation2MUL = new Operation();
        operation2MUL.setOperationOne(operation2DIV);
        operation2MUL.setOperationTwo(operation3DIV);
        operation2MUL.setOperatorType(OperatorType.MUL);

        // ------------------------------------------------------------
        //задаем 123
        Operation operationNumb123 = new Operation();
        operationNumb123.setNumber(123.0);
        // задаем 1235
        Operation operationNum1235 = new Operation();
        operationNum1235.setNumber(1235.0);
        // задаем SUM
        Operation operation3SUM = new Operation();
        operation3SUM.setOperationOne(operationNum123);
        operation3SUM.setOperationTwo(operationNum1235);
        operation3SUM.setOperatorType(OperatorType.SUM);

        //задаем 55
        Operation operationNumb55 = new Operation();
        operationNumb55.setNumber(55.0);
        //задаем 1111
        Operation operationNum1111 = new Operation();
        operationNum1111.setNumber(1111.0);
        //задаем MUL
        Operation operation3MUL = new Operation();
        operation3MUL.setOperationOne(operationNumb55);
        operation3MUL.setOperationTwo(operationNum1111);
        operation3MUL.setOperatorType(OperatorType.MUL);

        //задаем SUB
        Operation operation3SUB = new Operation();
        operation3SUB.setOperationOne(operation3SUM);
        operation3SUB.setOperationTwo(operation3MUL);
        operation3SUB.setOperatorType(OperatorType.SUB);

        // задаем окончательный оператор SUB
        Operation operationGenera2SUB = new Operation();
        operationGenera2SUB.setOperationOne(operation2MUL);
        operationGenera2SUB.setOperationTwo(operation3SUB);
        operationGenera2SUB.setOperatorType(OperatorType.SUB);

        list.add(operationGenera2SUB);

        return list;
    }
}
