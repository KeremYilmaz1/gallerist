package com.KeremYilmaz.galerist.starter.dto.IU;

import com.KeremYilmaz.galerist.starter.enums.CarStatusType;
import com.KeremYilmaz.galerist.starter.enums.CurrencyType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DtoCarIU {
    @NotNull
    private String plate;
    @NotNull
    private String brand;
    @NotNull
    private String model;
    @NotNull
    private Integer productionYear;
    @NotNull
    private BigDecimal price;
    @NotNull
    private CurrencyType currencyType;
    @NotNull
    private BigDecimal damageRecordValue;
    @NotNull
    private CarStatusType carStatusType;
}
