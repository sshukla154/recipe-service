package com.infosys.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * @author 'Seemant Shukla' on '15/11/2022'
 */

@ControllerAdvice
public class RecipeExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RecipeErrorMessage> resourceNotFoundException(ResourceNotFoundException resourceNotFoundException, WebRequest webRequest) {
        RecipeErrorMessage recipeErrorMessage = new RecipeErrorMessage(HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(), resourceNotFoundException.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<RecipeErrorMessage>(recipeErrorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RecipeErrorMessage> globalExceptionHandler(Exception exception, WebRequest webRequest) {
        RecipeErrorMessage message = new RecipeErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<RecipeErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
