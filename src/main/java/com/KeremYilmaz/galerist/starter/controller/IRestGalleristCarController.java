package com.KeremYilmaz.galerist.starter.controller;

import com.KeremYilmaz.galerist.starter.dto.DtoGalleristCar;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoGalleristCarIU;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRestGalleristCarController {
    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);

    public Boolean deleteGalleristCar(Long id);

    Page<DtoGalleristCar> findCarsByGalleristId(Long galleristId, Pageable pageable);
}
