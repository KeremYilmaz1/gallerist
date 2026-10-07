package com.KeremYilmaz.galerist.starter.controller.impl;

import com.KeremYilmaz.galerist.starter.controller.IRestGalleristCarController;
import com.KeremYilmaz.galerist.starter.dto.DtoGalleristCar;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoGalleristCarIU;
import com.KeremYilmaz.galerist.starter.service.IGalleristCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/list/gallerist/{galleristId}")
    @Override
    public Page<DtoGalleristCar> findCarsByGalleristId(
            @PathVariable Long galleristId,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return galleristCarService.findCarsByGalleristId(galleristId, pageable);
    }
}
