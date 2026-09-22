package com.KeremYilmaz.galerist.starter.service.impl;

import com.KeremYilmaz.galerist.starter.dto.CurrencyRateResponse;
import com.KeremYilmaz.galerist.starter.dto.DtoSoldCar;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoSoldCarIU;
import com.KeremYilmaz.galerist.starter.entity.Account;
import com.KeremYilmaz.galerist.starter.entity.Car;
import com.KeremYilmaz.galerist.starter.entity.Customer;
import com.KeremYilmaz.galerist.starter.entity.Gallerist;
import com.KeremYilmaz.galerist.starter.exception.BaseException;
import com.KeremYilmaz.galerist.starter.exception.ErrorMessage;
import com.KeremYilmaz.galerist.starter.exception.MessageType;
import com.KeremYilmaz.galerist.starter.repository.CarRepository;
import com.KeremYilmaz.galerist.starter.repository.CustomerRepository;
import com.KeremYilmaz.galerist.starter.repository.GalleristRepository;
import com.KeremYilmaz.galerist.starter.service.ICurrencyRatesService;
import com.KeremYilmaz.galerist.starter.service.ISoldCarService;
import com.KeremYilmaz.galerist.starter.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

@Service
public class SoldCarServiceImpl implements ISoldCarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private ICurrencyRatesService currencyRatesService;


    public BigDecimal convertCustomerAmountToUSD(Customer customer) {
        CurrencyRateResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

        BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);
        return customerUSDAmount;
    }

    private boolean checkAmount(DtoSoldCarIU dtoSoldCarIU) {

        Optional<Customer> optionalCustomer = customerRepository.findById(dtoSoldCarIU.getCustomerId());
        if (optionalCustomer.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , dtoSoldCarIU.getCustomerId().toString()));
        }
        Optional<Car> optionalCar = carRepository.findById(dtoSoldCarIU.getCarId());
        if(optionalCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , dtoSoldCarIU.getCarId().toString()));
        }
        BigDecimal customerAmountToUSD = convertCustomerAmountToUSD(optionalCustomer.get());

        if(customerAmountToUSD.compareTo(optionalCar.get().getPrice())>=0){
            return true;
        }
        return false;
    }


    @Override
    public DtoSoldCar buyCar(DtoSoldCarIU dtoSoldCarIU) {
        if(!checkAmount(dtoSoldCarIU)){
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH , dtoSoldCarIU.getCustomerId().toString()));
        }
        return null;
    }
}
