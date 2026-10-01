package com.KeremYilmaz.galerist.starter.service;

import com.KeremYilmaz.galerist.starter.dto.DtoCar;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoCarIU;
import com.KeremYilmaz.galerist.starter.enums.CarStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ICarService {
    public DtoCar saveCar(DtoCarIU dtoCarIU);

    public DtoCar findCarById(Long id);

    public DtoCar updateCar(Long id, DtoCarIU dtoCarIU);

    public boolean deleteCar(Long id);

    Page<DtoCar> findAllPageable(CarStatusType status, Pageable pageable);
}
