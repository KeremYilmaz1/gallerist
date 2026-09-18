package com.KeremYilmaz.galerist.starter.service;

import com.KeremYilmaz.galerist.starter.dto.DtoAccount;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoAccountIU;

public interface IAccountService {
    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
}
