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
import com.KeremYilmaz.galerist.starter.repository.SoldCarRepository;
import com.KeremYilmaz.galerist.starter.service.IGalleristCarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private SoldCarRepository soldCarRepository;

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

        if(galleristCarRepository.existsByCarId(dtoGalleristCarIU.getCarId())){
            throw new BaseException(new ErrorMessage(MessageType.CAR_ALREADY_IN_GALLERY , dtoGalleristCarIU.getCarId().toString()));
        }

        if(soldCarRepository.existsByCarId(dtoGalleristCarIU.getCarId())){
            throw new BaseException(new ErrorMessage(MessageType.CAR_IS_ALREADY_SOLD , dtoGalleristCarIU.getCarId().toString()));
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

    @Override
    public Boolean deleteGalleristCar(Long id) {
        Optional<GalleristCar> optionalGalleristCar = galleristCarRepository.findById(id);

        if(optionalGalleristCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , id.toString()));
        }

        Long carId = optionalGalleristCar.get().getCar().getId();
        if(soldCarRepository.existsByCarId(carId)){
            throw new BaseException(new ErrorMessage(MessageType.CAR_IS_ALREADY_SOLD , carId.toString()));
        }

        galleristCarRepository.delete(optionalGalleristCar.get());
        return true;
    }

    @Override
    public Page<DtoGalleristCar> findCarsByGalleristId(Long galleristId, Pageable pageable) {
        if (!galleristRepository.existsById(galleristId)) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, galleristId.toString()));
        }

        Page<GalleristCar> galleristCarPage = galleristCarRepository.findByGalleristId(galleristId, pageable);

        Page<DtoGalleristCar> dtoGalleristCarPage = galleristCarPage.map(galleristCar -> {
            DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
            BeanUtils.copyProperties(galleristCar, dtoGalleristCar);

            DtoCar dtoCar = new DtoCar();
            BeanUtils.copyProperties(galleristCar.getCar(), dtoCar);
            dtoGalleristCar.setDtoCar(dtoCar);

            DtoGallerist dtoGallerist = new DtoGallerist();
            BeanUtils.copyProperties(galleristCar.getGallerist(), dtoGallerist);

            DtoAddress dtoAddress = new DtoAddress();
            BeanUtils.copyProperties(galleristCar.getGallerist().getAddress(), dtoAddress);
            dtoGallerist.setAddress(dtoAddress);

            dtoGalleristCar.setDtoGallerist(dtoGallerist);
            return dtoGalleristCar;
        });

        return dtoGalleristCarPage;
    }
}
