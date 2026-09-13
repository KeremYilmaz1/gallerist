package com.KeremYilmaz.galerist.starter.controller;

import com.KeremYilmaz.galerist.starter.dto.AuthRequest;
import com.KeremYilmaz.galerist.starter.dto.AuthResponse;
import com.KeremYilmaz.galerist.starter.dto.DtoUser;
import com.KeremYilmaz.galerist.starter.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {
    public RootEntity<DtoUser> register(AuthRequest authRequest);

    public RootEntity<AuthResponse> authenticate(AuthRequest authRequest);

    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest refreshTokenRequest);
}
