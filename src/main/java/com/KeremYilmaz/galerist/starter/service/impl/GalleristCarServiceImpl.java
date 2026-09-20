package com.KeremYilmaz.galerist.starter.service.impl;

import com.KeremYilmaz.galerist.starter.dto.DtoAddress;
import com.KeremYilmaz.galerist.starter.dto.DtoCar;
import com.KeremYilmaz.galerist.starter.dto.DtoGallerist;
import com.KeremYilmaz.galerist.starter.dto.DtoGalleristCar;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoGalleristCarIU;
import com.KeremYilmaz.galerist.starter.entity.Car;
import com.KeremYilmaz.galerist.starter.entity.Gallerist;
import com.KeremYilmaz.galerist.starter.entity.GalleristCar;
import com.KeremYilmaz.galerist.starter.exception.BaseException;
import com.KeremYilmaz.galerist.starter.exception.ErrorMessage;
import com.KeremYilmaz.galerist.starter.exception.MessageType;
import com.KeremYilmaz.galerist.starter.repository.CarRepository;
import com.KeremYilmaz.galerist.starter.repository.GalleristCarRepository;
import com.KeremYilmaz.galerist.starter.repository.GalleristRepository;
import com.KeremYilmaz.galerist.starter.service.IGalleristCarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService {

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private GalleristCarRepository galleristCarRepository;

    private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        GalleristCar galleristCar = new GalleristCar();
        galleristCar.setCreateTime(new Date());

        Optional<Gallerist> optionalGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
        if (optionalGallerist.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , dtoGalleristCarIU.getGalleristId().toString()));
        }

        Optional<Car> optionalCar = carRepository.findById(dtoGalleristCarIU.getCarId());
        if (optionalCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , dtoGalleristCarIU.getCarId().toString()));
        }

        galleristCar.setCar(optionalCar.get());
        galleristCar.setGallerist(optionalGallerist.get());

        return galleristCar;
    }


    @Override
    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));

        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
        DtoCar dtoCar = new DtoCar();
        DtoGallerist  dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();

        BeanUtils.copyProperties(savedGalleristCar,dtoGalleristCar);
        BeanUtils.copyProperties(savedGalleristCar.getCar(), dtoCar);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist(), dtoGallerist);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), dtoAddress);

        dtoGallerist.setAddress(dtoAddress);
        dtoGalleristCar.setDtoCar(dtoCar);
        dtoGalleristCar.setDtoGallerist(dtoGallerist);
        return dtoGalleristCar;
    }
}
