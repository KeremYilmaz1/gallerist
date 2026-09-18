package com.KeremYilmaz.galerist.starter.service;

import com.KeremYilmaz.galerist.starter.dto.DtoCustomer;
import com.KeremYilmaz.galerist.starter.dto.DtoCustomerIU;

public interface ICustomerService {
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
}
