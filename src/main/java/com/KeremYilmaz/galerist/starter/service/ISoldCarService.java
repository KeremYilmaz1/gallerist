package com.KeremYilmaz.galerist.starter.service;

import com.KeremYilmaz.galerist.starter.dto.DtoSoldCar;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoSoldCarIU;

public interface ISoldCarService {
    public DtoSoldCar buyCar(DtoSoldCarIU dtoSoldCarIU);
}
