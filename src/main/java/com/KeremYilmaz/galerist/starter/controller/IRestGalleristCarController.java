package com.KeremYilmaz.galerist.starter.controller;

import com.KeremYilmaz.galerist.starter.dto.DtoGalleristCar;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoGalleristCarIU;

public interface IRestGalleristCarController {
    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
