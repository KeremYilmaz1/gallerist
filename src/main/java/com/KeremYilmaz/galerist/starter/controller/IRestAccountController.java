package com.KeremYilmaz.galerist.starter.controller;

import com.KeremYilmaz.galerist.starter.dto.DtoAccount;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoAccountIU;

public interface IRestAccountController {
    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
}
