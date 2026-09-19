package com.KeremYilmaz.galerist.starter.dto;

import com.KeremYilmaz.galerist.starter.enums.CarStatusType;
import com.KeremYilmaz.galerist.starter.enums.CurrencyType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DtoCar extends BaseDto{

    private String plate;

    private String brand;

    private String model;

    private Integer productionYear;

    private BigDecimal price;

    private CurrencyType currencyType;

    private BigDecimal damageRecordValue;

    private CarStatusType carStatusType;
}
