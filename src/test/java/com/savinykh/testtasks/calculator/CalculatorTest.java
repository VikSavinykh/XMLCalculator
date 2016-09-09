package com.savinykh.testtasks.calculator;

import com.savinykh.testtasks.calculator.calculator.Calculator;
import com.savinykh.testtasks.calculator.domain.Operation;
import com.savinykh.testtasks.calculator.domain.OperatorType;
import com.savinykh.testtasks.calculator.exception.ApplicationException;
import com.savinykh.testtasks.calculator.exception.ErrorMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by ManUnited on 02.07.2016.
 * класс CalculatorTest для проверки результата вычисления
 */
public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void initCalculator(){
        this.calculator = new Calculator();
    }

    /**
     * Тест для метода calculate(Operation operation) для prepareOperation1()
     */
    @Test
    public void calculateTest(){
        double result = calculator.calculate(prepareOperation1());
        Assert.assertEquals("Выражение 1 посчитано неверно!", -126.0, result, 0.000001);
    }
    /**
     * Тест для метода calculate(Operation operation) для prepareOperation2()
     */
    @Test(expected=ApplicationException.class)
    public void calculateDivByZeroTest(){
        Operation operationDivByZero = prepareOperationDivByZero();
        try{
            calculator.calculate(operationDivByZero);
        }catch (ApplicationException exception){
            Assert.assertEquals("Не соответствующая ошибка о делении на ноль!", ErrorMessage.DIV_BY_ZERO, exception.getErrorMessage());
            throw exception;
        }
    }

    /**
     * Получить выражение 1: (6+8)*(3-12) = -126
     */
    private Operation prepareOperation1(){
        Operation generalOperationMul = new Operation();
        generalOperationMul.setOperatorType(OperatorType.MUL);


        Operation operationSum = new Operation();
        operationSum.setOperatorType(OperatorType.SUM);

        Operation number6 = new Operation();
        number6.setNumber(6.0);
        operationSum.setOperationOne(number6);

        Operation number8 = new Operation();
        number8.setNumber(8.0);
        operationSum.setOperationTwo(number8);

        generalOperationMul.setOperationOne(operationSum);


        Operation operationSub = new Operation();
        operationSub.setOperatorType(OperatorType.SUB);

        Operation number3 = new Operation();
        number3.setNumber(3.0);
        operationSub.setOperationOne(number3);

        Operation number12 = new Operation();
        number12.setNumber(12.0);
        operationSub.setOperationTwo(number12);

        generalOperationMul.setOperationTwo(operationSub);

        return generalOperationMul;
    }

    /**
     * Получить выражение 2: 2/0 = деление на ноль запрещено
     */
    private Operation prepareOperationDivByZero(){
        Operation generalOperationDiv = new Operation();
        generalOperationDiv.setOperatorType(OperatorType.DIV);

        Operation number2 = new Operation();
        number2.setNumber(2.0);
        generalOperationDiv.setOperationOne(number2);

        Operation number0 = new Operation();
        number0.setNumber(0.0);
        generalOperationDiv.setOperationTwo(number0);

        return generalOperationDiv;
    }
}
