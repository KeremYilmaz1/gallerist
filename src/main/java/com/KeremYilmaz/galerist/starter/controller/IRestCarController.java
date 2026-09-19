package com.KeremYilmaz.galerist.starter.controller;

import com.KeremYilmaz.galerist.starter.dto.DtoCar;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoCarIU;

public interface IRestCarController {
    public DtoCar saveCar(DtoCarIU dtoCarIU);
}
