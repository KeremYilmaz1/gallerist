package com.KeremYilmaz.galerist.starter.controller.impl;

import com.KeremYilmaz.galerist.starter.controller.IRestCarController;
import com.KeremYilmaz.galerist.starter.dto.DtoCar;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoCarIU;
import com.KeremYilmaz.galerist.starter.service.ICarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarController implements IRestCarController {

    @Autowired
    private ICarService carService;

    @PostMapping("/save")
    @Override
    public DtoCar saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {
        return carService.saveCar(dtoCarIU);
    }
}
