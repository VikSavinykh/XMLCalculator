package com.savinykh.testtasks.calculator.domain;

/**
 * Created by ManUnited on 30.06.2016.
 * Класс Operation для создания объектов, с помощью которых будет подсчитано исходное выражение
 */
public class Operation {
    private Double number;
    private Operation operationOne;
    private Operation operationTwo;
    private OperatorType operatorType;

    public Operation() {
    }

    public Operation getOperationOne() {
        return operationOne;
    }

    public Operation getOperationTwo() {
        return operationTwo;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public void setOperationOne(Operation operationOne) {
        this.operationOne = operationOne;
    }

    public void setOperationTwo(Operation operationTwo) {
        this.operationTwo = operationTwo;
    }

    public void setOperatorType(OperatorType operatorType) {
        this.operatorType = operatorType;
    }

    public OperatorType getOperatorType() {
        return operatorType;
    }
}
