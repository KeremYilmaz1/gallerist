package com.KeremYilmaz.galerist.starter.controller.impl;

import com.KeremYilmaz.galerist.starter.controller.IRestGalleristCarController;
import com.KeremYilmaz.galerist.starter.dto.DtoGalleristCar;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoGalleristCarIU;
import com.KeremYilmaz.galerist.starter.service.IGalleristCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/galleristCar")
public class RestGallerisCarControllerImpl implements IRestGalleristCarController {

    @Autowired
    private IGalleristCarService galleristCarService;

    @PostMapping("/save")
    @Override
    public DtoGalleristCar saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
        return galleristCarService.saveGalleristCar(dtoGalleristCarIU);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public Boolean deleteGalleristCar(@PathVariable Long id) {
        return galleristCarService.deleteGalleristCar(id);
    }
}
