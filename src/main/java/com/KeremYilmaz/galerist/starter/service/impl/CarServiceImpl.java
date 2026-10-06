package com.KeremYilmaz.galerist.starter.service.impl;

import com.KeremYilmaz.galerist.starter.dto.DtoCar;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoCarIU;
import com.KeremYilmaz.galerist.starter.entity.Car;
import com.KeremYilmaz.galerist.starter.entity.GalleristCar;
import com.KeremYilmaz.galerist.starter.enums.CarStatusType;
import com.KeremYilmaz.galerist.starter.exception.BaseException;
import com.KeremYilmaz.galerist.starter.exception.ErrorMessage;
import com.KeremYilmaz.galerist.starter.exception.MessageType;
import com.KeremYilmaz.galerist.starter.repository.CarRepository;
import com.KeremYilmaz.galerist.starter.repository.GalleristCarRepository;
import com.KeremYilmaz.galerist.starter.repository.GalleristRepository;
import com.KeremYilmaz.galerist.starter.repository.SoldCarRepository;
import com.KeremYilmaz.galerist.starter.service.ICarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private GalleristCarRepository galleristCarRepository;

    @Autowired
    private SoldCarRepository soldCarRepository;


    private Car createCar(DtoCarIU dtoCarIU){

        Car createdCar = new Car();
        createdCar.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoCarIU,createdCar);

        return createdCar;
    }


    @Override
    public DtoCar saveCar(DtoCarIU dtoCarIU) {
        if(carRepository.existsByPlate(dtoCarIU.getPlate())){
            throw new BaseException(new ErrorMessage(MessageType.PLATE_ALREADY_EXISTS , dtoCarIU.getPlate()));
        }

        Car createdCar = createCar(dtoCarIU);
        carRepository.save(createdCar);
        DtoCar dtoCar = new DtoCar();
        BeanUtils.copyProperties(createdCar,dtoCar);

        return dtoCar;
    }

    @Override
    public DtoCar findCarById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if(optionalCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , id.toString()));
        }

        DtoCar dtoCar = new DtoCar();
        BeanUtils.copyProperties(optionalCar.get() , dtoCar);
        return dtoCar;
    }


    @Override
    public DtoCar updateCar(Long id, DtoCarIU dtoCarIU) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if(optionalCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , id.toString()));
        }

        if(carRepository.existsByPlateAndIdNot(dtoCarIU.getPlate() , id)){
            throw new BaseException(new ErrorMessage(MessageType.PLATE_ALREADY_EXISTS , dtoCarIU.getPlate()));
        }

        Car carToBeUpdated = optionalCar.get();
        BeanUtils.copyProperties(dtoCarIU,carToBeUpdated);
        Car savedCar = carRepository.save(carToBeUpdated);

        DtoCar dtoCar = new DtoCar();
        BeanUtils.copyProperties(savedCar,dtoCar);

        return dtoCar;
    }

    @Override
    public boolean deleteCar(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if(optionalCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , id.toString()));
        }

        if(galleristCarRepository.existsByCarId(optionalCar.get().getId())){
            throw new BaseException(new ErrorMessage(MessageType.CAR_IN_USE , id.toString()));
        }

        if(soldCarRepository.existsByCarId(optionalCar.get().getId())){
            throw new BaseException(new ErrorMessage(MessageType.CAR_IN_USE , id.toString()));
        }

        carRepository.delete(optionalCar.get());

        return true;
    }

    @Override
    public Page<DtoCar> findAllPageable(CarStatusType status, Pageable pageable) {
        Page<Car> carPage;

        if (status == null) {
            carPage = carRepository.findAll(pageable);
        } else {
            carPage = carRepository.findByCarStatusType(status, pageable);
        }

        Page<DtoCar> dtoCarPage = carPage.map(this::toDtoCar);
        return dtoCarPage;
    }

    private DtoCar toDtoCar(Car car) {
        DtoCar dtoCar = new DtoCar();
        BeanUtils.copyProperties(car, dtoCar);
        return dtoCar;
    }
}
