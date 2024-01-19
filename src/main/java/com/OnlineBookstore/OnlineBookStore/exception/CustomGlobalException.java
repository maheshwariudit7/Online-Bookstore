package com.OnlineBookstore.OnlineBookStore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomGlobalException extends RuntimeException{

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(BookNotFoundException exception,
                                                                        WebRequest request){

        ErrorDetails errorDetails=new ErrorDetails(exception.getMessage(), LocalDateTime.now(),
                request.getDescription(false),"BOOK_NOT_FOUND");

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);


    }
}
