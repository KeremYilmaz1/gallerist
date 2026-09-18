package com.KeremYilmaz.galerist.starter.controller.impl;

import com.KeremYilmaz.galerist.starter.controller.IRestAccountController;
import com.KeremYilmaz.galerist.starter.dto.DtoAccount;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoAccountIU;
import com.KeremYilmaz.galerist.starter.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountControllerImpl implements IRestAccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/save")
    @Override
    public DtoAccount saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
        return accountService.saveAccount(dtoAccountIU);
    }
}
