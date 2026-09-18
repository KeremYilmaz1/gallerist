package com.KeremYilmaz.galerist.starter.controller.impl;

import com.KeremYilmaz.galerist.starter.controller.IRestCustomerController;
import com.KeremYilmaz.galerist.starter.dto.DtoCustomer;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoCustomerIU;
import com.KeremYilmaz.galerist.starter.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/customer")
public class RestCustomerController implements IRestCustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping("/save")
    @Override
    public DtoCustomer saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
        return customerService.saveCustomer(dtoCustomerIU);
    }
}
