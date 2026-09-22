package com.KeremYilmaz.galerist.starter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSoldCar extends BaseDto{
    private DtoGallerist dtoGallerist;
    private DtoCar dtoCar;
    private DtoCustomer dtoCustomer;
}
