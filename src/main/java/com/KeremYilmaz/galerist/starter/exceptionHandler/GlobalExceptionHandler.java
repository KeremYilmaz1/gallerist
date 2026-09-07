package com.KeremYilmaz.galerist.starter.exceptionHandler;

import com.KeremYilmaz.galerist.starter.exception.BaseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {BaseException.class})
    public void handleBaseException(BaseException bEx){

    }
}
