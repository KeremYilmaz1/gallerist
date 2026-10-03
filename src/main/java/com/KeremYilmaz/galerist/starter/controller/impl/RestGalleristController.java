package com.KeremYilmaz.galerist.starter.controller.impl;

import com.KeremYilmaz.galerist.starter.controller.IRestGalleristController;
import com.KeremYilmaz.galerist.starter.dto.DtoGallerist;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoGalleristIU;
import com.KeremYilmaz.galerist.starter.service.IGalleristService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/gallerist")
public class RestGalleristController implements IRestGalleristController {

    @Autowired
    private IGalleristService galleristService;

    @PostMapping("/save")
    @Override
    public DtoGallerist saveGallerist(@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {
        return galleristService.saveGallerist(dtoGalleristIU);
    }

    @GetMapping("/get/{id}")
    @Override
    public DtoGallerist findGalleristById(@PathVariable Long id) {
        return galleristService.findGalleristById(id);
    }

    @PutMapping("/update/{id}")
    @Override
    public DtoGallerist updateGallerist(@PathVariable Long id,@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {
        return galleristService.updateGallerist(id,dtoGalleristIU);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public Boolean deleteGallerist(@PathVariable Long id) {
        return galleristService.deleteGallerist(id);
    }

    @GetMapping("/list/pageable")
    @Override
    public Page<DtoGallerist> findAllPageable(@PageableDefault(size = 10, sort = "id")Pageable pageable) {
        return galleristService.findAllPageable(pageable);
    }
}
