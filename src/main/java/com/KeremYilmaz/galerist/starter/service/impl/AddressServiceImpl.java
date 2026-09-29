package com.KeremYilmaz.galerist.starter.service.impl;

import com.KeremYilmaz.galerist.starter.dto.DtoAddress;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoAddressIU;
import com.KeremYilmaz.galerist.starter.entity.Address;
import com.KeremYilmaz.galerist.starter.exception.BaseException;
import com.KeremYilmaz.galerist.starter.exception.ErrorMessage;
import com.KeremYilmaz.galerist.starter.exception.MessageType;
import com.KeremYilmaz.galerist.starter.repository.AddressRepository;
import com.KeremYilmaz.galerist.starter.service.IAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;


    private Address createAddress(DtoAddressIU dtoAddressIU){
        Address address = new Address();
        address.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoAddressIU , address);
        return address;
    }


    @Override
    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
        DtoAddress dtoAddress = new DtoAddress();

        Address savedAddress = addressRepository.save(createAddress(dtoAddressIU));
        BeanUtils.copyProperties(savedAddress,dtoAddress);
        return dtoAddress;
    }

    @Override
    public boolean deleteAddress(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if(optionalAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , ""));
        }
        addressRepository.delete(optionalAddress.get());
        return true;
    }

    @Override
    public DtoAddress findById(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if(optionalAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , id.toString()));
        }

        DtoAddress dtoAddress = new DtoAddress();

        BeanUtils.copyProperties(optionalAddress.get() , dtoAddress);
        return dtoAddress;
    }

    @Override
    public DtoAddress updateAddress(Long id, DtoAddressIU dtoAddressIU) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if(optionalAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , id.toString()));
        }

        Address addressToBeUpdated = optionalAddress.get();
        BeanUtils.copyProperties(dtoAddressIU , addressToBeUpdated);

        Address savedAddress = addressRepository.save(addressToBeUpdated);

        DtoAddress dtoAddress = new DtoAddress();
        BeanUtils.copyProperties(savedAddress,dtoAddress);

        return dtoAddress;
    }

    @Override
    public Page<DtoAddress> findAllPageable(Pageable pageable) {
        Page<Address> addressPage = addressRepository.findAll(pageable);

        return addressPage.map(address -> {
            DtoAddress dtoAddress = new DtoAddress();
            BeanUtils.copyProperties(address, dtoAddress);
            return dtoAddress;
        });
    }

}
