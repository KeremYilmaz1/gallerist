package com.KeremYilmaz.galerist.starter.service.impl;

import com.KeremYilmaz.galerist.starter.dto.DtoAccount;
import com.KeremYilmaz.galerist.starter.dto.DtoAccountUpdate;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoAccountIU;
import com.KeremYilmaz.galerist.starter.entity.Account;
import com.KeremYilmaz.galerist.starter.exception.BaseException;
import com.KeremYilmaz.galerist.starter.exception.ErrorMessage;
import com.KeremYilmaz.galerist.starter.exception.MessageType;
import com.KeremYilmaz.galerist.starter.repository.AccountRepository;
import com.KeremYilmaz.galerist.starter.repository.CustomerRepository;
import com.KeremYilmaz.galerist.starter.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private Account createAccount(DtoAccountIU dtoAccountIU){
        Account account = new Account();
        account.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoAccountIU,account);

        return account;
    }

    @Override
    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {
        if(accountRepository.existsByAccountNo(dtoAccountIU.getAccountNo())){
            throw new BaseException(new ErrorMessage(MessageType.ACCOUNT_NO_IN_USE , dtoAccountIU.getAccountNo()));
        }


        if(accountRepository.existsByIban(dtoAccountIU.getIban())){
            throw new BaseException(new ErrorMessage(MessageType.IBAN_IN_USE , dtoAccountIU.getIban()));
        }

        Account account = createAccount(dtoAccountIU);
        DtoAccount dtoAccount = new DtoAccount();

        Account savedAccount = accountRepository.save(account);
        BeanUtils.copyProperties(savedAccount,dtoAccount);
        return dtoAccount;
    }

    @Transactional
    @Override
    public DtoAccount deposit(Long id, BigDecimal amount) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if(optionalAccount.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , id.toString()));
        }

        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new BaseException(new ErrorMessage(MessageType.INVALID_AMOUNT , String.valueOf(amount)));
        }

        Account updatedAccount = optionalAccount.get();
        BigDecimal amountBefore = updatedAccount.getAmount();
        updatedAccount.setAmount(amountBefore.add(amount));
        Account savedAccount = accountRepository.save(updatedAccount);

        DtoAccount dtoAccount = new DtoAccount();
        BeanUtils.copyProperties(savedAccount,dtoAccount);

        return dtoAccount;
    }

    @Transactional
    @Override
    public DtoAccount withdraw(Long id, BigDecimal amount) {

        Optional<Account> optionalAccount = accountRepository.findById(id);
        if(optionalAccount.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , id.toString()));
        }

        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new BaseException(new ErrorMessage(MessageType.INVALID_AMOUNT , String.valueOf(amount)));
        }

        Account updatedAccount = optionalAccount.get();
        BigDecimal amountBefore = updatedAccount.getAmount();

        if(amountBefore.compareTo(amount) < 0){
            throw new BaseException(new ErrorMessage(MessageType.INSUFFICIENT_BALANCE , amountBefore.toString()));
        }

        updatedAccount.setAmount(amountBefore.subtract(amount));
        Account savedAccount = accountRepository.save(updatedAccount);

        DtoAccount dtoAccount = new DtoAccount();
        BeanUtils.copyProperties(savedAccount,dtoAccount);

        return dtoAccount;
    }

    @Override
    public DtoAccount updateAccount(Long id, DtoAccountUpdate dtoAccountUpdate) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , id.toString()));
        }

        if(accountRepository.existsByAccountNoAndIdNot(dtoAccountUpdate.getAccountNo(), id)){
            throw new BaseException(new ErrorMessage(MessageType.ACCOUNT_NO_IN_USE , dtoAccountUpdate.getAccountNo()));
        }

        if(accountRepository.existsByIbanAndIdNot(dtoAccountUpdate.getIban(), id)){
            throw new BaseException(new ErrorMessage(MessageType.IBAN_IN_USE , dtoAccountUpdate.getIban()));
        }

        Account accountToBeUpdated = optionalAccount.get();
        BeanUtils.copyProperties(dtoAccountUpdate , accountToBeUpdated);
        Account savedAccount = accountRepository.save(accountToBeUpdated);

        DtoAccount dtoAccount = new DtoAccount();
        BeanUtils.copyProperties(savedAccount,dtoAccount);

        return dtoAccount;
    }

    @Override
    public boolean deleteAccount(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , id.toString()));
        }

        if(customerRepository.existsByAccountId(id)){
            throw new BaseException(new ErrorMessage(MessageType.ACCOUNT_IN_USE , id.toString()));
        }

        accountRepository.delete(optionalAccount.get());
        return true;
    }

    @Override
    public Page<DtoAccount> findAllPageable(Pageable pageable) {
        Page<Account> accountPage = accountRepository.findAll(pageable);

        Page<DtoAccount> dtoAccountPage = accountPage.map(account -> {
            DtoAccount dtoAccount = new DtoAccount();
            BeanUtils.copyProperties(account, dtoAccount);
            return dtoAccount;
        });

        return dtoAccountPage;
    }
}
