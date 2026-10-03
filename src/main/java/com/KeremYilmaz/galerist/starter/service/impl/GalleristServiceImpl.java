package com.KeremYilmaz.galerist.starter.service.impl;

import com.KeremYilmaz.galerist.starter.dto.DtoAddress;
import com.KeremYilmaz.galerist.starter.dto.DtoGallerist;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoGalleristIU;
import com.KeremYilmaz.galerist.starter.entity.Address;
import com.KeremYilmaz.galerist.starter.entity.Gallerist;
import com.KeremYilmaz.galerist.starter.exception.BaseException;
import com.KeremYilmaz.galerist.starter.exception.ErrorMessage;
import com.KeremYilmaz.galerist.starter.exception.MessageType;
import com.KeremYilmaz.galerist.starter.repository.AddressRepository;
import com.KeremYilmaz.galerist.starter.repository.GalleristCarRepository;
import com.KeremYilmaz.galerist.starter.repository.GalleristRepository;
import com.KeremYilmaz.galerist.starter.repository.SoldCarRepository;
import com.KeremYilmaz.galerist.starter.service.IGalleristService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GalleristServiceImpl implements IGalleristService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private GalleristCarRepository galleristCarRepository;

    @Autowired
    private SoldCarRepository soldCarRepository;

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

    @Override
    public DtoGallerist findGalleristById(Long id) {
        Optional<Gallerist> optionalGallerist = galleristRepository.findById(id);
        if(optionalGallerist.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , id.toString()));
        }

        DtoGallerist dtoGallerist = new DtoGallerist();
        BeanUtils.copyProperties(optionalGallerist.get() , dtoGallerist);

        DtoAddress dtoAddress = new DtoAddress();
        BeanUtils.copyProperties(optionalGallerist.get().getAddress() , dtoAddress);

        dtoGallerist.setAddress(dtoAddress);
        return dtoGallerist;
    }

    @Override
    public DtoGallerist updateGallerist(Long id, DtoGalleristIU dtoGalleristIU) {

        Optional<Gallerist> optionalGallerist = galleristRepository.findById(id);
        if (optionalGallerist.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , id.toString()));
        }

        Optional<Address> optionalAddress = addressRepository.findById(dtoGalleristIU.getAddressId());
        if(optionalAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , dtoGalleristIU.getAddressId().toString()));
        }
        BeanUtils.copyProperties(dtoGalleristIU,optionalGallerist.get());
        optionalGallerist.get().setAddress(optionalAddress.get());

        Gallerist savedGallerist = galleristRepository.save(optionalGallerist.get());

        DtoAddress dtoAddress = new DtoAddress();
        DtoGallerist dtoGallerist = new DtoGallerist();
        BeanUtils.copyProperties(optionalAddress.get() , dtoAddress);
        BeanUtils.copyProperties(savedGallerist,dtoGallerist);
        dtoGallerist.setAddress(dtoAddress);

        return dtoGallerist;
    }

    @Override
    public Boolean deleteGallerist(Long id) {
        Optional<Gallerist> optionalGallerist = galleristRepository.findById(id);
        if (optionalGallerist.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , id.toString()));
        }

        if (galleristCarRepository.existsByGalleristId(id) || soldCarRepository.existsByGalleristId(id)){
            throw new BaseException(new ErrorMessage(MessageType.GALLERIST_IN_USE , id.toString()));
        }

        galleristRepository.delete(optionalGallerist.get());

        return true;
    }

    @Override
    public Page<DtoGallerist> findAllPageable(Pageable pageable) {
        Page<Gallerist> galleristPage = galleristRepository.findAll(pageable);

        Page<DtoGallerist> dtoGalleristPage = galleristPage.map(gallerist -> {
            DtoGallerist dtoGallerist = new DtoGallerist();
            BeanUtils.copyProperties(gallerist, dtoGallerist);

            DtoAddress dtoAddress = new DtoAddress();
            BeanUtils.copyProperties(gallerist.getAddress(), dtoAddress);
            dtoGallerist.setAddress(dtoAddress);

            return dtoGallerist;
        });

        return dtoGalleristPage;
    }
}
