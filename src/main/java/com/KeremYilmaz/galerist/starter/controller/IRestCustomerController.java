package com.KeremYilmaz.galerist.starter.controller;

import com.KeremYilmaz.galerist.starter.dto.DtoCustomer;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoCustomerIU;

public interface IRestCustomerController {
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
}
