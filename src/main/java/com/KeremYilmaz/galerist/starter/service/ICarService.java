package com.KeremYilmaz.galerist.starter.service;

import com.KeremYilmaz.galerist.starter.dto.DtoCar;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoCarIU;

public interface ICarService {
    public DtoCar saveCar(DtoCarIU dtoCarIU);
}
