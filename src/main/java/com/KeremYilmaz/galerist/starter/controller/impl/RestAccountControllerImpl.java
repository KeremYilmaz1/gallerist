package com.KeremYilmaz.galerist.starter.controller.impl;

import com.KeremYilmaz.galerist.starter.controller.IRestAccountController;
import com.KeremYilmaz.galerist.starter.dto.DtoAccount;
import com.KeremYilmaz.galerist.starter.dto.DtoAmount;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoAccountIU;
import com.KeremYilmaz.galerist.starter.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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

    @PutMapping("/deposit/{id}")
    @Override
    public DtoAccount deposit(@PathVariable Long id, @Valid @RequestBody DtoAmount amount) {
        return accountService.deposit(id,amount.getAmount());
    }

    @PutMapping("/withdraw/{id}")
    @Override
    public DtoAccount withdraw(@PathVariable Long id, @Valid @RequestBody DtoAmount amount) {
        return accountService.withdraw(id,amount.getAmount());
    }


}
