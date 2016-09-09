package com.savinykh.testtasks.calculator.exception;

/**
 * Created by ManUnited on 02.07.2016.
 * Класс ApplicationException для перехвата ошибок
 */
public class ApplicationException extends RuntimeException {

    private ErrorMessage errorMessage;
    
    public ApplicationException(ErrorMessage errorMessage, Object... args) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

    public ApplicationException(ErrorMessage errorMessage, Throwable cause, Object... args) {
        super(errorMessage.getMessage(), cause);
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

}
