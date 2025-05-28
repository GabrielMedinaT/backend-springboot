package com.ieselrincon.service;

import com.ieselrincon.model.UserLogin;
import com.ieselrincon.repository.jpa.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

    @Autowired
    private UserLoginRepository userLoginRepository;

    public UserLogin findByUser(String user) {
        return userLoginRepository.findByUser(user);
    }

    public void save(UserLogin userLogin) {
        userLoginRepository.save(userLogin);
    }
}
