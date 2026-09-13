package com.KeremYilmaz.galerist.starter.service;

import com.KeremYilmaz.galerist.starter.dto.AuthRequest;
import com.KeremYilmaz.galerist.starter.dto.AuthResponse;
import com.KeremYilmaz.galerist.starter.dto.DtoUser;
import com.KeremYilmaz.galerist.starter.dto.RefreshTokenRequest;

public interface IAuthenticationService {
    public DtoUser register(AuthRequest authRequest);

    public AuthResponse authenticate(AuthRequest authRequest);

    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
