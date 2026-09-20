package com.KeremYilmaz.galerist.starter.dto;

import com.KeremYilmaz.galerist.starter.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoGalleristCar extends BaseEntity {

    private DtoGallerist dtoGallerist;

    private DtoCar dtoCar;
}
