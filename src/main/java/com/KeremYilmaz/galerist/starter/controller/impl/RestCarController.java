package com.KeremYilmaz.galerist.starter.controller.impl;

import com.KeremYilmaz.galerist.starter.controller.IRestCarController;
import com.KeremYilmaz.galerist.starter.dto.DtoCar;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoCarIU;
import com.KeremYilmaz.galerist.starter.enums.CarStatusType;
import com.KeremYilmaz.galerist.starter.service.ICarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get/{id}")
    @Override
    public DtoCar findCarById(@PathVariable Long id) {
        return carService.findCarById(id);
    }

    @PutMapping("update/{id}")
    @Override
    public DtoCar updateCar(@PathVariable Long id, @Valid @RequestBody DtoCarIU dtoCarIU) {
        return carService.updateCar(id , dtoCarIU);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public boolean deleteCar(@PathVariable Long id) {
        return carService.deleteCar(id);
    }

    @GetMapping("list/pageable")
    @Override
    public Page<DtoCar> findAllPageable(@RequestParam(required = false) CarStatusType status, @PageableDefault(size = 10, sort = "id")Pageable pageable) {
        return carService.findAllPageable(status, pageable) ;
    }
}
