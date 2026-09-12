package com.KeremYilmaz.galerist.starter.service;

import com.KeremYilmaz.galerist.starter.dto.AuthRequest;
import com.KeremYilmaz.galerist.starter.dto.AuthResponse;
import com.KeremYilmaz.galerist.starter.dto.DtoUser;

public interface IAuthenticationService {
    public DtoUser register(AuthRequest authRequest);

    public AuthResponse authenticate(AuthRequest authRequest);
}
