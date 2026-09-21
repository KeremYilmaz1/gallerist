package com.KeremYilmaz.galerist.starter.service;

import com.KeremYilmaz.galerist.starter.dto.CurrencyRateResponse;

public interface ICurrencyRatesService {
    public CurrencyRateResponse getCurrencyRates(String startDate, String endDate);
}
