package com.KeremYilmaz.galerist.starter.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CurrencyRateResponse {

    private Integer totalCount;

    private List<CurrencyRatesItems> items;
}
