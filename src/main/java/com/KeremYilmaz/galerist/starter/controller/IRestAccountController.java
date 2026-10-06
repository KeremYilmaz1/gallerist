package com.KeremYilmaz.galerist.starter.controller;

import com.KeremYilmaz.galerist.starter.dto.DtoAccount;
import com.KeremYilmaz.galerist.starter.dto.DtoAmount;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoAccountIU;

import java.math.BigDecimal;

public interface IRestAccountController {
    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);

    public DtoAccount deposit(Long id, DtoAmount amount);

    public DtoAccount withdraw(Long id, DtoAmount amount);
}
