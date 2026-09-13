package com.KeremYilmaz.galerist.starter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAddress extends BaseDto{

    private String city;

    private String district;

    private String neihgborhood;

    private String street;
}
