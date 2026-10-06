package com.KeremYilmaz.galerist.starter.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAccountUpdate {

    @NotBlank
    private String accountNo;

    @NotBlank
    private String iban;
}
