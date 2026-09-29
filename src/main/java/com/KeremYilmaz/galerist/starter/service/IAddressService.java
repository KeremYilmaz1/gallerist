package com.KeremYilmaz.galerist.starter.service;

import com.KeremYilmaz.galerist.starter.dto.DtoAddress;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoAddressIU;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAddressService {
    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);

    public boolean deleteAddress(Long id);

    public DtoAddress findById(Long id);

    public DtoAddress updateAddress(Long id , DtoAddressIU dtoAddressIU);

    public Page<DtoAddress> findAllPageable(Pageable pageable);
}
