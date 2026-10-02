package com.KeremYilmaz.galerist.starter.controller.impl;

import com.KeremYilmaz.galerist.starter.controller.IRestCustomerController;
import com.KeremYilmaz.galerist.starter.dto.DtoCustomer;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoCustomerIU;
import com.KeremYilmaz.galerist.starter.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get/{id}")
    @Override
    public DtoCustomer findCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @PutMapping("/update/{id}")
    @Override
    public DtoCustomer updateCustomer(@PathVariable Long id, @Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
        return customerService.updateCustomer(id,dtoCustomerIU);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public boolean deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }

    @GetMapping("/list/pageable")
    @Override
    public Page<DtoCustomer> findAllPageable(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return customerService.findAllPageable(pageable);
    }
}
