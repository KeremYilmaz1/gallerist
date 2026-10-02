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
import com.KeremYilmaz.galerist.starter.repository.SoldCarRepository;
import com.KeremYilmaz.galerist.starter.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private SoldCarRepository soldCarRepository;

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

    @Override
    public DtoCustomer findCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if(optionalCustomer.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , id.toString()));
        }

        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAddress dtoAddress = new DtoAddress();
        DtoAccount dtoAccount = new DtoAccount();

        Address address = optionalCustomer.get().getAddress();
        Account account = optionalCustomer.get().getAccount();

        BeanUtils.copyProperties(address,dtoAddress);
        BeanUtils.copyProperties(account,dtoAccount);
        BeanUtils.copyProperties(optionalCustomer.get() , dtoCustomer);

        dtoCustomer.setAccount(dtoAccount);
        dtoCustomer.setAddress(dtoAddress);
        return dtoCustomer;
    }

    @Override
    public DtoCustomer updateCustomer(Long id, DtoCustomerIU dtoCustomerIU) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , id.toString()));
        }

        Optional<Account> optionalAccount = accountRepository.findById(dtoCustomerIU.getAccountId());
        if (optionalAccount.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , dtoCustomerIU.getAccountId().toString()));
        }

        Optional<Address> optionalAddress = addressRepository.findById(dtoCustomerIU.getAddressId());
        if (optionalAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , dtoCustomerIU.getAddressId().toString()));
        }

        if (customerRepository.existsByAccountIdAndIdNot(dtoCustomerIU.getAccountId(), id)) {
            throw new BaseException(new ErrorMessage(MessageType.ACCOUNT_IN_USE, dtoCustomerIU.getAccountId().toString()));
        }

        if (customerRepository.existsByTcknAndIdNot(dtoCustomerIU.getTckn(), id)) {
            throw new BaseException(new ErrorMessage(MessageType.TCKN_ALREADY_EXISTS, dtoCustomerIU.getTckn()));
        }

        BeanUtils.copyProperties(dtoCustomerIU , optionalCustomer.get());
        optionalCustomer.get().setAccount(optionalAccount.get());
        optionalCustomer.get().setAddress(optionalAddress.get());

        Customer savedCustomer = customerRepository.save(optionalCustomer.get());

        DtoCustomer dtoCustomer = new DtoCustomer();
        BeanUtils.copyProperties(savedCustomer , dtoCustomer);

        DtoAddress dtoAddress = new DtoAddress();
        DtoAccount dtoAccount = new DtoAccount();
        BeanUtils.copyProperties(optionalAddress.get() , dtoAddress);
        BeanUtils.copyProperties(optionalAccount.get() , dtoAccount);

        dtoCustomer.setAddress(dtoAddress);
        dtoCustomer.setAccount(dtoAccount);
        return dtoCustomer;
    }

    @Override
    public boolean deleteCustomer(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , id.toString()));
        }

        if (soldCarRepository.existsByCustomerId(id)) {
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_IN_USE, id.toString()));
        }

        customerRepository.delete(optionalCustomer.get());
        return true;
    }

    @Override
    public Page<DtoCustomer> findAllPageable(Pageable pageable) {
        Page<Customer> customerPage = customerRepository.findAll(pageable);

        Page<DtoCustomer> dtoCustomerPage = customerPage.map(customer -> {
            DtoCustomer dtoCustomer = new DtoCustomer();
            BeanUtils.copyProperties(customer, dtoCustomer);

            DtoAddress dtoAddress = new DtoAddress();
            BeanUtils.copyProperties(customer.getAddress(), dtoAddress);
            dtoCustomer.setAddress(dtoAddress);

            DtoAccount dtoAccount = new DtoAccount();
            BeanUtils.copyProperties(customer.getAccount(), dtoAccount);
            dtoCustomer.setAccount(dtoAccount);

            return dtoCustomer;
        });

        return dtoCustomerPage;
    }


}
