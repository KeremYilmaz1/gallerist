package com.KeremYilmaz.galerist.starter.service;

import com.KeremYilmaz.galerist.starter.dto.DtoGallerist;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoGalleristIU;

public interface IGalleristService {

    DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);
}
