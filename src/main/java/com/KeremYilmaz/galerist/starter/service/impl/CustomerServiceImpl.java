package com.KeremYilmaz.galerist.starter.service.impl;

import com.KeremYilmaz.galerist.starter.dto.DtoAccount;
import com.KeremYilmaz.galerist.starter.dto.DtoAddress;
import com.KeremYilmaz.galerist.starter.dto.DtoCustomer;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoCustomerIU;
import com.KeremYilmaz.galerist.starter.entity.Account;
import com.KeremYilmaz.galerist.starter.entity.Address;
import com.KeremYilmaz.galerist.starter.entity.Customer;
import com.KeremYilmaz.galerist.starter.exception.BaseException;
import com.KeremYilmaz.galerist.starter.exception.ErrorMessage;
import com.KeremYilmaz.galerist.starter.exception.MessageType;
import com.KeremYilmaz.galerist.starter.repository.AccountRepository;
import com.KeremYilmaz.galerist.starter.repository.AddressRepository;
import com.KeremYilmaz.galerist.starter.repository.CustomerRepository;
import com.KeremYilmaz.galerist.starter.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Customer createCustomer(DtoCustomerIU dtoCustomerIU){

        Optional<Address> optionalAddress = addressRepository.findById(dtoCustomerIU.getAddressId());
        if(optionalAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , dtoCustomerIU.getAccountId().toString()));
        }

        Optional<Account> optionalAccount = accountRepository.findById(dtoCustomerIU.getAccountId());
        if(optionalAccount.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , dtoCustomerIU.getAccountId().toString()));
        }

        Customer createdCustomer = new Customer();
        createdCustomer.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoCustomerIU,createdCustomer);

        createdCustomer.setAccount(optionalAccount.get());
        createdCustomer.setAddress(optionalAddress.get());

        return createdCustomer;
    }

    @Override
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {

        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAddress dtoAddress = new DtoAddress();
        DtoAccount dtoAccount = new DtoAccount();

        Customer createdCustomer = createCustomer(dtoCustomerIU);

        customerRepository.save(createdCustomer);
        BeanUtils.copyProperties(createdCustomer,dtoCustomer);

        BeanUtils.copyProperties(createdCustomer.getAccount() , dtoAccount);
        BeanUtils.copyProperties(createdCustomer.getAddress() , dtoAddress);

        dtoCustomer.setAccount(dtoAccount);
        dtoCustomer.setAddress(dtoAddress);
        return dtoCustomer;
    }
}
