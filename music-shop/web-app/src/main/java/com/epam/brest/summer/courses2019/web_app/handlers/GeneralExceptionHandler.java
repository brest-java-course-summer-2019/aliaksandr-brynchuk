package com.epam.brest.summer.courses2019.web_app.handlers;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(){
        return "exception";
    }
}
