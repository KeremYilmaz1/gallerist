package com.KeremYilmaz.galerist.starter.controller;

import com.KeremYilmaz.galerist.starter.dto.DtoAccount;
import com.KeremYilmaz.galerist.starter.dto.DtoAccountIU;
import com.KeremYilmaz.galerist.starter.dto.DtoAddressIU;

public interface IRestAccountController {
    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
}
