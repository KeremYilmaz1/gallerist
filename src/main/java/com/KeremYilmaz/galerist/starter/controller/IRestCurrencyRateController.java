package com.KeremYilmaz.galerist.starter.controller;

import com.KeremYilmaz.galerist.starter.dto.CurrencyRateResponse;

public interface IRestCurrencyRateController {
    public CurrencyRateResponse getCurrencyRates(String startDate, String endDate);
}
