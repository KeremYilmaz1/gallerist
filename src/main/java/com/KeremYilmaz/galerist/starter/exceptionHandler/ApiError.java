package com.KeremYilmaz.galerist.starter.exceptionHandler;

public class ApiError<E> {

    private Integer status;

    private Exception<E> exception;
}
