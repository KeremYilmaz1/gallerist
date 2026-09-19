package com.KeremYilmaz.galerist.starter.service.impl;

import com.KeremYilmaz.galerist.starter.dto.DtoCar;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoCarIU;
import com.KeremYilmaz.galerist.starter.entity.Car;
import com.KeremYilmaz.galerist.starter.repository.CarRepository;
import com.KeremYilmaz.galerist.starter.service.ICarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarRepository carRepository;


    private Car createCar(DtoCarIU dtoCarIU){

        Car createdCar = new Car();
        createdCar.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoCarIU,createdCar);

        return createdCar;
    }


    @Override
    public DtoCar saveCar(DtoCarIU dtoCarIU) {

        Car createdCar = createCar(dtoCarIU);
        carRepository.save(createdCar);
        DtoCar dtoCar = new DtoCar();
        BeanUtils.copyProperties(createdCar,dtoCar);

        return dtoCar;
    }
}
