package com.savinykh.testtasks.calculator.exception;

/**
 * Created by ManUnited on 02.07.2016.
 * ErrorMessage содержит возможную ошибку и сообщение для нее
 */
public enum ErrorMessage {

    DIV_BY_ZERO("There is division by zero in one expression");

    ErrorMessage(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }
}
