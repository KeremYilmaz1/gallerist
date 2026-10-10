package com.KeremYilmaz.galerist.starter.service.impl;

import com.KeremYilmaz.galerist.starter.dto.*;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoSoldCarIU;
import com.KeremYilmaz.galerist.starter.entity.*;
import com.KeremYilmaz.galerist.starter.enums.CarStatusType;
import com.KeremYilmaz.galerist.starter.exception.BaseException;
import com.KeremYilmaz.galerist.starter.exception.ErrorMessage;
import com.KeremYilmaz.galerist.starter.exception.MessageType;
import com.KeremYilmaz.galerist.starter.repository.*;
import com.KeremYilmaz.galerist.starter.service.ICurrencyRatesService;
import com.KeremYilmaz.galerist.starter.service.ISoldCarService;
import com.KeremYilmaz.galerist.starter.utils.DateUtils;
import org.springframework.beans.BeanUtils;
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
    private SoldCarRepository soldCarRepository;

    @Autowired
    private GalleristCarRepository galleristCarRepository;

    @Autowired
    private ICurrencyRatesService currencyRatesService;


    public BigDecimal convertCustomerAmountToUSD(Customer customer) {
        CurrencyRateResponse currencyRatesResponse = currencyRatesService.getCurrencyRates("25-09-2026" , "25-09-2026"); //DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date())
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

    public boolean checkCarStatus(Long carId){

        Optional<Car> optCar = carRepository.findById(carId);
        if(optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SOLD.name())){
           return false;
        }
        return true;
    }

    public BigDecimal remaningCustomerAccount(Customer customer , Car car){

        BigDecimal customerAmountToUSD = convertCustomerAmountToUSD(customer);
        BigDecimal customerRemainingUSDAmount = customerAmountToUSD.subtract(car.getPrice());

        CurrencyRateResponse currencyRateResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
        BigDecimal usd = new BigDecimal(currencyRateResponse.getItems().get(0).getUsd());

        return customerRemainingUSDAmount.multiply(usd);
    }


    @Override
    public DtoSoldCar buyCar(DtoSoldCarIU dtoSoldCarIU) {

        if(!galleristCarRepository.existsByGalleristIdAndCarId(dtoSoldCarIU.getGalleristId(),dtoSoldCarIU.getCarId())){
            throw new BaseException(new ErrorMessage(MessageType.CAR_NOT_IN_GALLERY , dtoSoldCarIU.getCarId().toString()));
        }

        if (!checkCarStatus(dtoSoldCarIU.getCarId())){
            throw new BaseException(new ErrorMessage(MessageType.CAR_IS_ALREADY_SOLD , dtoSoldCarIU.getCarId().toString()));
        }

        if(!checkAmount(dtoSoldCarIU)){
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH , dtoSoldCarIU.getCustomerId().toString()));
        }

        SoldCar savedSoldCar = soldCarRepository.save(createSoldCar(dtoSoldCarIU));

        Car car = savedSoldCar.getCar();
        car.setCarStatusType(CarStatusType.SOLD);

        carRepository.save(car); // save metodu eğer kayıt yoksa yeni kayıt oluşturur eğer varsa üzerine yazar burada oluşturma değil update için kullandım


        Customer customer = savedSoldCar.getCustomer();
        customer.getAccount().setAmount(remaningCustomerAccount(customer , car));

        customerRepository.save(customer);
        return toDTO(savedSoldCar);
    }

    private SoldCar createSoldCar(DtoSoldCarIU dtoSoldCarIU){
        SoldCar soldCar = new SoldCar();
        soldCar.setCreateTime(new Date());

        soldCar.setCustomer(customerRepository.findById(dtoSoldCarIU.getCustomerId()).orElse(null));
        soldCar.setGallerist(galleristRepository.findById(dtoSoldCarIU.getGalleristId()).orElse(null));
        soldCar.setCar(carRepository.findById(dtoSoldCarIU.getCarId()).orElse(null));

        return soldCar;
    }


    public DtoSoldCar toDTO(SoldCar soldCar){  //Üst taraf çok kalabalıklaştığı için dto conversion işlemini burada yaptım

        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();
        DtoCustomer dtoCustomer = new DtoCustomer();

        DtoSoldCar dtoSoldCar = new DtoSoldCar();

        BeanUtils.copyProperties(soldCar , dtoSoldCar);
        BeanUtils.copyProperties(soldCar.getCar() , dtoCar);
        BeanUtils.copyProperties(soldCar.getCustomer() , dtoCustomer);
        BeanUtils.copyProperties(soldCar.getGallerist() , dtoGallerist);

        dtoSoldCar.setDtoCar(dtoCar);
        dtoSoldCar.setDtoGallerist(dtoGallerist);
        dtoSoldCar.setDtoCustomer(dtoCustomer);
        return dtoSoldCar;
    }
}
