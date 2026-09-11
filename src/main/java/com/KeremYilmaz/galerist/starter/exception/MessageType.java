package com.KeremYilmaz.galerist.starter.exception;

import lombok.Getter;

@Getter
public enum MessageType {
    NO_RECORD_EXIST("1004" , "kayıt bulunamadı"),
    TOKEN_IS_EXPIRED("1005" , "token is expired"),
    GENERAL_EXCEPTION("9999" , "Genel bir hata oluştu");

    private String code;

    private String message;

    MessageType(String code, String message){
        this.code = code;

        this.message = message;
    }
}
