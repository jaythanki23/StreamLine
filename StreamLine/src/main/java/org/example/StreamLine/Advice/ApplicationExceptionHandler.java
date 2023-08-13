package org.example.StreamLine.Advice;

import org.example.StreamLine.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @Autowired
    private ExceptionHandlerObject exceptionHandlerObject;

    public ApplicationExceptionHandler(ExceptionHandlerObject exceptionHandlerObject) {
        this.exceptionHandlerObject = exceptionHandlerObject;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        exceptionHandlerObject = new ExceptionHandlerObject();
        return exceptionHandlerObject.createErrorMap(exception.getBindingResult().getFieldErrors());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleUserNotFoundException(UserNotFoundException exception) {
        exceptionHandlerObject = new ExceptionHandlerObject();
        return exceptionHandlerObject.setKeyValuePair("Message", exception.getMessage());
    }

}
