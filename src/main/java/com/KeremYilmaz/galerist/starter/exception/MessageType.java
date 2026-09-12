package com.KeremYilmaz.galerist.starter.exception;

import lombok.Getter;

@Getter
public enum MessageType {
    NO_RECORD_EXIST("1004" , "kayıt bulunamadı"),
    TOKEN_IS_EXPIRED("1005" , "token'in süresi bitmiştir"),
    USERNAME_NOT_FOUND("1006" , "username bulunamadı"),
    GENERAL_EXCEPTION("9999" , "Genel bir hata oluştu"),
    USERNAME_OR_PASSWORD_INVALID("1007" , "Kullanıcı adı yada şifre hatalı");

    private String code;

    private String message;

    MessageType(String code, String message){
        this.code = code;

        this.message = message;
    }
}
