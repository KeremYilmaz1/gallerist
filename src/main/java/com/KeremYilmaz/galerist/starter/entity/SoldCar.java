package com.KeremYilmaz.galerist.starter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sold_car" ,
uniqueConstraints = @UniqueConstraint(columnNames = {"gallerist_id" , "car_id" , "customer_id"} ,
        name = "uq_gallerist_car_customer"))
public class SoldCar extends BaseEntity{

    @ManyToOne
    private Gallerist gallerist;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Customer customer;
}
