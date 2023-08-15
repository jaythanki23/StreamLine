package org.example.StreamLine.Advice;

import org.example.StreamLine.Exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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

    // For request body
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
        return exceptionHandlerObject.setKeyValuePair("Error", exception.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FileOrDescriptionNotFoundException.class)
    public Map<String, String> handleFileOrDescriptionNotFoundException(FileOrDescriptionNotFoundException exception) {
        exceptionHandlerObject = new ExceptionHandlerObject();
        return exceptionHandlerObject.setKeyValuePair("Error", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(PostNotFoundException.class)
    public Map<String, String> handlePostNotFoundException(PostNotFoundException exception) {
        exceptionHandlerObject = new ExceptionHandlerObject();
        return exceptionHandlerObject.setKeyValuePair("Error", exception.getMessage());
    }

    // For request parameters
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Map<String, String> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        exceptionHandlerObject = new ExceptionHandlerObject();
        return exceptionHandlerObject.setKeyValuePair("Error", exception.getParameterName() + " is missing");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CommentNotFoundException.class)
    public Map<String, String> handleCommentNotFoundException(CommentNotFoundException exception) {
        exceptionHandlerObject = new ExceptionHandlerObject();
        return exceptionHandlerObject.setKeyValuePair("Error", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public Map<String, String> handleCommentNotFoundException(UserAlreadyExistsException exception) {
        exceptionHandlerObject = new ExceptionHandlerObject();
        return exceptionHandlerObject.setKeyValuePair("Error", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(StoryNotFoundException.class)
    public Map<String, String> handleStoryNotFoundException(StoryNotFoundException exception) {
        exceptionHandlerObject = new ExceptionHandlerObject();
        return exceptionHandlerObject.setKeyValuePair("Error", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HashtagNotFoundException.class)
    public Map<String, String> handleStoryNotFoundException(HashtagNotFoundException exception) {
        exceptionHandlerObject = new ExceptionHandlerObject();
        return exceptionHandlerObject.setKeyValuePair("Error", exception.getMessage());
    }


}
