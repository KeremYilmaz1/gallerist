package com.KeremYilmaz.galerist.starter.controller;

import com.KeremYilmaz.galerist.starter.dto.AuthRequest;
import com.KeremYilmaz.galerist.starter.dto.AuthResponse;
import com.KeremYilmaz.galerist.starter.dto.DtoUser;

public interface IRestAuthenticationController {
    public RootEntity<DtoUser> register(AuthRequest authRequest);

    public RootEntity<AuthResponse> authenticate(AuthRequest authRequest);
}
