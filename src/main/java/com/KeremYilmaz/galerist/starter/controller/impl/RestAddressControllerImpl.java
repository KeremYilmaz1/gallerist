package com.KeremYilmaz.galerist.starter.controller.impl;

import com.KeremYilmaz.galerist.starter.controller.IRestAddressController;
import com.KeremYilmaz.galerist.starter.dto.DtoAddress;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoAddressIU;
import com.KeremYilmaz.galerist.starter.service.IAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl implements IRestAddressController {

    @Autowired
    private IAddressService addressService;

    @PostMapping("/save")
    @Override
    public DtoAddress saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
        return addressService.saveAddress(dtoAddressIU);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public boolean deleteAddress(@PathVariable Long id) {
        return addressService.deleteAddress(id);
    }


    @GetMapping("/get/{id}")
    @Override
    public DtoAddress findById(@PathVariable Long id) {
        return addressService.findById(id);
    }

    @PutMapping("update/{id}")
    @Override
    public DtoAddress updateAddress(@PathVariable Long id, @RequestBody DtoAddressIU dtoAddressIU) {
        return addressService.updateAddress(id,dtoAddressIU);
    }

    @GetMapping("/list")
    @Override
    public Page<DtoAddress> findAllPageable(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return addressService.findAllPageable(pageable);
    }
}
