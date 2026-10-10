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
    CAR_IS_ALREADY_SOLD("1012" , "araba zaten satılmış"),
    ADDRESS_IN_USE("1013" , "adres kullanım içerisinde silinemez"),
    PLATE_ALREADY_EXISTS("1014" , "bu plakaya ait farklı bir araba var"),
    CAR_IN_USE("1015" , "Araç kullanımda olduğu için silinemez"),
    ACCOUNT_IN_USE("1016" , "Hesap zaten bir müşteriye bağlı"),
    TCKN_ALREADY_EXISTS("1017" , "tc numarası başka bir hesap tarafından kullanım durumunda"),
    CUSTOMER_IN_USE("1018" , "customer kullanımda olduğu için silinemez"),
    GALLERIST_IN_USE("1019" , "gallerist kullanım içinde olduğu için silinemez"),
    IBAN_IN_USE("1020" , "iban farklı bir hesap tarafından kullanılmakta"),
    ACCOUNT_NO_IN_USE("1021" , "account no başka bir hesap tarafından kullanım durumundadır"),
    INVALID_AMOUNT("1022" , "tutar geçersiz"),
    INSUFFICIENT_BALANCE("1023", "Yetersiz bakiye"),
    CAR_ALREADY_IN_GALLERY("1024", "Araç zaten bir galeriye bağlı"),
    CAR_NOT_IN_GALLERY("1025", "Araç bu galeriye ait değil"),
    USERNAME_OR_PASSWORD_INVALID("1007" , "Kullanıcı adı yada şifre hatalı");

    private String code;

    private String message;

    MessageType(String code, String message){
        this.code = code;

        this.message = message;
    }
}
