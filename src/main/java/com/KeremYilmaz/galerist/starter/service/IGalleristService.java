package com.KeremYilmaz.galerist.starter.service;

import com.KeremYilmaz.galerist.starter.dto.DtoGallerist;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoCustomerIU;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoGalleristIU;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IGalleristService {

    DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);

    DtoGallerist findGalleristById(Long id);

    DtoGallerist updateGallerist(Long id, DtoGalleristIU dtoGalleristIU);

    Boolean deleteGallerist(Long id);

    Page<DtoGallerist> findAllPageable(Pageable pageable);
}
