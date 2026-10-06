package com.KeremYilmaz.galerist.starter.service;

import com.KeremYilmaz.galerist.starter.dto.DtoAccount;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoAccountIU;

import java.math.BigDecimal;

public interface IAccountService {
    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);

    public DtoAccount deposit(Long id, BigDecimal amount);

    public DtoAccount withdraw(Long id, BigDecimal amount);
}
