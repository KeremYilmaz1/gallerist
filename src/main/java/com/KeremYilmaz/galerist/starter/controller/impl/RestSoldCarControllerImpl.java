package com.KeremYilmaz.galerist.starter.controller.impl;

import com.KeremYilmaz.galerist.starter.controller.IRestSoldCarController;
import com.KeremYilmaz.galerist.starter.dto.DtoSoldCar;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoSoldCarIU;
import com.KeremYilmaz.galerist.starter.service.ISoldCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/api/sold-car")
public class RestSoldCarControllerImpl implements IRestSoldCarController {

    @Autowired
    private ISoldCarService soldCarService;

    @PostMapping("/save")
    @Override
    public DtoSoldCar buyCar(@Valid @RequestBody DtoSoldCarIU dtoSoldCarIU) {
        return soldCarService.buyCar(dtoSoldCarIU);
    }
}
