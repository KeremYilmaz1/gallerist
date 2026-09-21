package com.KeremYilmaz.galerist.starter.controller.impl;

import com.KeremYilmaz.galerist.starter.controller.IRestCurrencyRateController;
import com.KeremYilmaz.galerist.starter.dto.CurrencyRateResponse;
import com.KeremYilmaz.galerist.starter.service.ICurrencyRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/api/")
public class RestCurrencyRateControllerImpl implements IRestCurrencyRateController {

    @Autowired
    private ICurrencyRatesService currencyRatesService;

    @GetMapping("currency-rates/")
    @Override
    public CurrencyRateResponse getCurrencyRates(@RequestParam String startDate, @RequestParam String endDate) {
        return currencyRatesService.getCurrencyRates(startDate, endDate);
    }
}
