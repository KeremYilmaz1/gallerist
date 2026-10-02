package com.KeremYilmaz.galerist.starter.service;

import com.KeremYilmaz.galerist.starter.dto.DtoCustomer;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoCustomerIU;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService {
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);

    public DtoCustomer findCustomerById(Long id);

    public DtoCustomer updateCustomer(Long id , DtoCustomerIU dtoCustomerIU);

    public boolean deleteCustomer(Long id);

    Page<DtoCustomer> findAllPageable(Pageable pageable);
}
