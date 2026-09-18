package com.KeremYilmaz.galerist.starter.controller;

import com.KeremYilmaz.galerist.starter.dto.DtoGallerist;
import com.KeremYilmaz.galerist.starter.dto.IU.DtoGalleristIU;

public interface IRestGalleristController {
    DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);
}
