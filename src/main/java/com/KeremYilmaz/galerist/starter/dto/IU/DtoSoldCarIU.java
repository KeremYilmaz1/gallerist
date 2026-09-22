package com.KeremYilmaz.galerist.starter.dto.IU;

import com.KeremYilmaz.galerist.starter.dto.DtoCar;
import com.KeremYilmaz.galerist.starter.dto.DtoCustomer;
import com.KeremYilmaz.galerist.starter.dto.DtoGallerist;
import com.KeremYilmaz.galerist.starter.entity.Car;
import com.KeremYilmaz.galerist.starter.entity.Customer;
import com.KeremYilmaz.galerist.starter.entity.Gallerist;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSoldCarIU {

    private Long customerId;
    private Long galleristId;
    private Long carId;
}
