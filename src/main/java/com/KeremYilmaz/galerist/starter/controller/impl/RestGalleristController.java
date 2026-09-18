package com.KeremYilmaz.galerist.starter.controller.impl;

import com.KeremYilmaz.galerist.starter.controller.IRestGalleristController;
import com.KeremYilmaz.galerist.starter.dto.DtoGallerist;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoGalleristIU;
import com.KeremYilmaz.galerist.starter.service.IGalleristService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
