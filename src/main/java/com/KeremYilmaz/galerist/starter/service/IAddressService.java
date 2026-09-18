package com.KeremYilmaz.galerist.starter.service;

import com.KeremYilmaz.galerist.starter.dto.DtoAddress;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoAddressIU;

public interface IAddressService {
    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
}
