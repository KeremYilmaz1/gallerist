package com.KeremYilmaz.galerist.starter.service.impl;

import com.KeremYilmaz.galerist.starter.dto.CurrencyRateResponse;
import com.KeremYilmaz.galerist.starter.exception.BaseException;
import com.KeremYilmaz.galerist.starter.exception.ErrorMessage;
import com.KeremYilmaz.galerist.starter.exception.MessageType;
import com.KeremYilmaz.galerist.starter.service.ICurrencyRatesService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurenncyRateServiceImpl implements ICurrencyRatesService {


    @Override
    public CurrencyRateResponse getCurrencyRates(String startDate, String endDate) {
        String rootUrl = "https://evds3.tcmb.gov.tr/igmevdsms-dis/" ;
        String series = "TP.DK.USD.S.YTL";
        String type = "json";

        String endpoint = rootUrl + "series=" + series + "&startDate="+startDate+"&endDate="+endDate+"&type="+type;

        HttpHeaders headers = new HttpHeaders();
        headers.set("key","DQS5LKa5rN");

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        try{
            ResponseEntity<CurrencyRateResponse> response = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<CurrencyRateResponse>() {
            });
            if(response.getStatusCode().is2xxSuccessful()){
                return response.getBody();
            }
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATE_ERROR_OCCURED , e.getMessage()));
        }

        return null;
    }
}
