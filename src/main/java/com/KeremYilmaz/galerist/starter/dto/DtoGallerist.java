package com.KeremYilmaz.galerist.starter.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoGallerist extends BaseDto{

    private String firstName;

    private String lastName;

    private DtoAddress address;
}
