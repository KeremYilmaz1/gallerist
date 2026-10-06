package com.KeremYilmaz.galerist.starter.service;

import com.KeremYilmaz.galerist.starter.dto.DtoAccount;
import com.KeremYilmaz.galerist.starter.dto.DtoAccountUpdate;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoAccountIU;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface IAccountService {
    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);

    public DtoAccount deposit(Long id, BigDecimal amount);

    public DtoAccount withdraw(Long id, BigDecimal amount);

    public DtoAccount updateAccount(Long id , DtoAccountUpdate dtoAccountUpdate);

    public boolean deleteAccount(Long id);

    Page<DtoAccount> findAllPageable(Pageable pageable);
}
