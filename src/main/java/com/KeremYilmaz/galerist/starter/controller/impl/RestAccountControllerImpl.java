package com.KeremYilmaz.galerist.starter.controller.impl;

import com.KeremYilmaz.galerist.starter.controller.IRestAccountController;
import com.KeremYilmaz.galerist.starter.dto.DtoAccount;
import com.KeremYilmaz.galerist.starter.dto.DtoAccountUpdate;
import com.KeremYilmaz.galerist.starter.dto.DtoAmount;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoAccountIU;
import com.KeremYilmaz.galerist.starter.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.parameters.P;
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

    @PutMapping("/update/{id}")
    @Override
    public DtoAccount updateAccount(@PathVariable Long id, @Valid @RequestBody DtoAccountUpdate dtoAccountUpdate) {
        return accountService.updateAccount(id,dtoAccountUpdate);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public boolean deleteAccount(@PathVariable Long id) {
        return accountService.deleteAccount(id);
    }

    @GetMapping("/list/pageable")
    @Override
    public Page<DtoAccount> findAllPageable(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return accountService.findAllPageable(pageable);
    }


}
