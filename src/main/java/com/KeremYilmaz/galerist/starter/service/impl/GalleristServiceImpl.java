package com.KeremYilmaz.galerist.starter.service.impl;

import com.KeremYilmaz.galerist.starter.dto.DtoAddress;
import com.KeremYilmaz.galerist.starter.dto.DtoGallerist;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoGalleristIU;
import com.KeremYilmaz.galerist.starter.entity.Address;
import com.KeremYilmaz.galerist.starter.entity.BaseEntity;
import com.KeremYilmaz.galerist.starter.entity.Gallerist;
import com.KeremYilmaz.galerist.starter.exception.BaseException;
import com.KeremYilmaz.galerist.starter.exception.ErrorMessage;
import com.KeremYilmaz.galerist.starter.exception.MessageType;
import com.KeremYilmaz.galerist.starter.repository.AddressRepository;
import com.KeremYilmaz.galerist.starter.repository.GalleristRepository;
import com.KeremYilmaz.galerist.starter.service.IGalleristService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GalleristServiceImpl implements IGalleristService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU){
        Optional<Address> optionalAddress = addressRepository.findById(dtoGalleristIU.getAddressId());
        if (optionalAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , dtoGalleristIU.getAddressId().toString()));
        }

        Gallerist createdGallerist = new Gallerist();
        createdGallerist.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoGalleristIU , createdGallerist);

        createdGallerist.setAddress(optionalAddress.get());
        return createdGallerist;
    }

    @Override
    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
        Gallerist createdGallerist = createGallerist(dtoGalleristIU);
        galleristRepository.save(createdGallerist);
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();

        BeanUtils.copyProperties(createdGallerist.getAddress() , dtoAddress);
        BeanUtils.copyProperties(createdGallerist , dtoGallerist);
        dtoGallerist.setAddress(dtoAddress);

        return dtoGallerist;
    }
}
