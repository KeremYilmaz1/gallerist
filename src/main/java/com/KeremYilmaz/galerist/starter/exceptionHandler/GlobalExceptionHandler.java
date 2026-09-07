package com.KeremYilmaz.galerist.starter.exceptionHandler;

import com.KeremYilmaz.galerist.starter.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<ApiError<?>> handleBaseException(BaseException bEx , WebRequest webRequest){
        return ResponseEntity.badRequest().body(createApiError(bEx.getMessage() , webRequest));
    }



    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError<Map<String, List<String>>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException notValidException
    , WebRequest webRequest){

        Map<String , List<String>> map = new HashMap<>();
        for (ObjectError objError : notValidException.getBindingResult().getAllErrors()) {
            String field = ((FieldError) objError).getField();

            if (map.containsKey(field)){
                map.put(field,addValue(map.get(field) , objError.getDefaultMessage()));
            }else{
                map.put(field , addValue(new ArrayList<>() , objError.getDefaultMessage()));
            }
        }
        return ResponseEntity.badRequest().body(createApiError(map , webRequest));
    }

    private List<String> addValue(List<String> list, String value){
         list.add(value);
         return list;
    }

        
    private String getHostName(){

        try {
            return Inet4Address.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public <E> ApiError<E> createApiError(E message , WebRequest webRequest){
        ApiError<E> apiError = new ApiError<>();
        apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        Exception<E> exception = new Exception<>();
        exception.setPath(webRequest.getDescription(false).substring(4));
        exception.setCreateTime(new Date());
        exception.setMessage(message);
        exception.setHostName(getHostName());

        apiError.setException(exception);
        return apiError;
    }

}
