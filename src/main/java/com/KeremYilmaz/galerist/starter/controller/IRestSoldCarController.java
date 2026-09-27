package com.KeremYilmaz.galerist.starter.controller;

import com.KeremYilmaz.galerist.starter.dto.DtoSoldCar;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoSoldCarIU;

public interface IRestSoldCarController {
    public DtoSoldCar buyCar(DtoSoldCarIU dtoSoldCarIU);
}
