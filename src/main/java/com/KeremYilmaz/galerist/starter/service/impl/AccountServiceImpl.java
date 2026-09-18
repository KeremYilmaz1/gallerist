package com.KeremYilmaz.galerist.starter.service.impl;

import com.KeremYilmaz.galerist.starter.dto.DtoAccount;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoAccountIU;
import com.KeremYilmaz.galerist.starter.entity.Account;
import com.KeremYilmaz.galerist.starter.repository.AccountRepository;
import com.KeremYilmaz.galerist.starter.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    private Account createAccount(DtoAccountIU dtoAccountIU){
        Account account = new Account();
        account.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoAccountIU,account);

        return account;
    }

    @Override
    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {
        Account account = createAccount(dtoAccountIU);
        DtoAccount dtoAccount = new DtoAccount();

        BeanUtils.copyProperties(account,dtoAccount);

        accountRepository.save(account);
        return dtoAccount;
    }
}
