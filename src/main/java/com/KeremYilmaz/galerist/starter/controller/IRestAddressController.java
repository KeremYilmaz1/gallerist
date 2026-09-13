package com.KeremYilmaz.galerist.starter.controller;

import com.KeremYilmaz.galerist.starter.dto.DtoAddress;
import com.KeremYilmaz.galerist.starter.dto.DtoAddressIU;

public interface IRestAddressController {
    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
}
