package com.KeremYilmaz.galerist.starter.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DtoCustomer extends BaseDto{

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String tckn;
    @NotNull
    private Date birthOfDate;
    @NotNull
    private DtoAddress address;
    @NotNull
    private DtoAccount account;
}
