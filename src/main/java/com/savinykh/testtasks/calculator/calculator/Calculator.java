package com.savinykh.testtasks.calculator.calculator;

import com.savinykh.testtasks.calculator.domain.Operation;
import com.savinykh.testtasks.calculator.domain.OperatorType;
import com.savinykh.testtasks.calculator.exception.ApplicationException;
import com.savinykh.testtasks.calculator.exception.ErrorMessage;

/**
 * Created by ManUnited on 30.06.2016.
 * Класс Calculator для подсчета результата по полученному выражению
 */
public class Calculator {

    /**
     * метод calculate возвращает конечное double число и передает объект для получения результата
     * @param operation - объект, над которым проводить операцию
     * @return - результат операции
     */
    public Double calculate(Operation operation) {
        double operationOne;
        double operationTwo;

        if (operation.getOperatorType() == null) {
            return operation.getNumber();
        } else {
            operationOne = calculate(operation.getOperationOne());
            operationTwo = calculate(operation.getOperationTwo());
            return calculateJustNumber(operationOne, operationTwo,
                operation.getOperatorType());
        }
    }

    /**
     * метод calculateJustNumber проводит математические операции (+, -, *, /) над выражениями
     * @param number1 - число 1 для проведения математической операции
     * @param number2 - число 2 для проведения математической операции
     * @param operatorType - какую математическую операцию произвести
     * @return - результат математической операции
     */
    private double calculateJustNumber(double number1, double number2,
        OperatorType operatorType) {
        switch (operatorType) {
            case SUM:
                return number1 + number2;
            case SUB:
                return number1 - number2;
            case MUL:
                return number1 * number2;
            case DIV: {
                if (number2 == 0) {
                    throw new ApplicationException(ErrorMessage.DIV_BY_ZERO);
                }
                return number1 / number2;
            }
        }
        return 0;
    }
}
