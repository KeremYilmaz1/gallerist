package com.KeremYilmaz.galerist.starter.dto.IU;

import com.KeremYilmaz.galerist.starter.dto.DtoAddress;
import com.KeremYilmaz.galerist.starter.entity.Address;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoGalleristIU {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Long addressId;
}
