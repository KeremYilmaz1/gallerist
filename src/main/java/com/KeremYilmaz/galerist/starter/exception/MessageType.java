package com.KeremYilmaz.galerist.starter.exception;

import lombok.Getter;

@Getter
public enum MessageType {
    NO_RECORD_EXIST("1004" , "kayıt bulunamadı"),
    TOKEN_IS_EXPIRED("1005" , "token'in süresi bitmiştir"),
    USERNAME_NOT_FOUND("1006" , "username bulunamadı"),
    GENERAL_EXCEPTION("9999" , "Genel bir hata oluştu"),
    REFRESH_TOKEN_NOT_FOUND("1008" , "refresh token bulunamadı"),
    REFRESH_TOKEN_IS_EXPIRED("1009" , "refresh token'in süresi doldu"),
    CURRENCY_RATE_ERROR_OCCURED("1010" , "döviz kuru alınamadı"),
    CUSTOMER_AMOUNT_IS_NOT_ENOUGH("1011" , "müşterinin parası yeterli değil"),
    USERNAME_OR_PASSWORD_INVALID("1007" , "Kullanıcı adı yada şifre hatalı");

    private String code;

    private String message;

    MessageType(String code, String message){
        this.code = code;

        this.message = message;
    }
}
