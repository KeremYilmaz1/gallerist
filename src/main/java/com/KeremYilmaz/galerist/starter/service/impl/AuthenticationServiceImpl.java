package com.KeremYilmaz.galerist.starter.service.impl;

import com.KeremYilmaz.galerist.starter.dto.AuthRequest;
import com.KeremYilmaz.galerist.starter.dto.DtoUser;
import com.KeremYilmaz.galerist.starter.entity.User;
import com.KeremYilmaz.galerist.starter.repository.UserRepository;
import com.KeremYilmaz.galerist.starter.service.IAuthenticationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
}
