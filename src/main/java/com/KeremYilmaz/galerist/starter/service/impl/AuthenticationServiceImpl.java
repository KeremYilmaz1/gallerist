package com.KeremYilmaz.galerist.starter.service.impl;

import com.KeremYilmaz.galerist.starter.dto.AuthRequest;
import com.KeremYilmaz.galerist.starter.dto.AuthResponse;
import com.KeremYilmaz.galerist.starter.dto.DtoUser;
import com.KeremYilmaz.galerist.starter.entity.RefreshToken;
import com.KeremYilmaz.galerist.starter.entity.User;
import com.KeremYilmaz.galerist.starter.exception.BaseException;
import com.KeremYilmaz.galerist.starter.exception.ErrorMessage;
import com.KeremYilmaz.galerist.starter.exception.MessageType;
import com.KeremYilmaz.galerist.starter.jwt.JWTService;
import com.KeremYilmaz.galerist.starter.repository.RefreshTokenRepository;
import com.KeremYilmaz.galerist.starter.repository.UserRepository;
import com.KeremYilmaz.galerist.starter.service.IAuthenticationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public User createUser(AuthRequest authRequest){
        User user = new User();
        user.setCreateTime(new Date());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        user.setUsername(authRequest.getUsername());

        return user;
    }

    @Override
    public DtoUser register(AuthRequest authRequest) {
        DtoUser dtoUser = new DtoUser();
        User user = createUser(authRequest);
        userRepository.save(user);

        BeanUtils.copyProperties(user,dtoUser);
        return dtoUser;
    }


    private RefreshToken createRefreshToken(User user){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreateTime(new Date());
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000*60*60*4));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);

        return refreshToken;
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {

        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername() , authRequest.getPassword());

            authenticationProvider.authenticate(authenticationToken);

            User user = userRepository.findByUsername(authRequest.getUsername()).get();

            String accessToken = jwtService.generateToken(user);
            RefreshToken refreshToken = createRefreshToken(user);

            refreshTokenRepository.save(refreshToken);

            return new AuthResponse(accessToken,refreshToken.getRefreshToken());
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID , e.getMessage()));
        }
    }
}
