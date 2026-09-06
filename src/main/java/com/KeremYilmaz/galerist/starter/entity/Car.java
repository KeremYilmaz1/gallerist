package com.KeremYilmaz.galerist.starter.entity;

import com.KeremYilmaz.galerist.starter.enums.CarStatusType;
import com.KeremYilmaz.galerist.starter.enums.CurrencyType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car extends BaseEntity{
    private String plate;

    private String brand;

    private String model;

    @Column(name = "production_year")
    private Integer productionYear;

    private BigDecimal price;

    @Column(name = "currency_type")
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @Column(name = "damage_record_value")
    private BigDecimal damageRecordValue;

    @Column(name = "car_status_type")
    @Enumerated(EnumType.STRING)
    private CarStatusType carStatusType;
}
