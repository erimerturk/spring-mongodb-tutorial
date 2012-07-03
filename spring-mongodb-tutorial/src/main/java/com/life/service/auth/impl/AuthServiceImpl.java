package com.life.service.auth.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.life.domain.Auth;
import com.life.domain.User;
import com.life.repository.AuthRepository;
import com.life.repository.UserRepository;

@Service("authService")
public class AuthServiceImpl
{
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private UserRepository userRepository;

    public User getUser(String autId)
    {
        Auth auth = authRepository.findById(autId);
        Preconditions.checkNotNull(auth, "Invaild auth key");
        return auth.getUser();
    }

    public void logout(String autId)
    {
        Auth auth = authRepository.findById(autId);
        User user = auth.getUser();
        user.setAuth(null);
        userRepository.save(user);
        authRepository.delete(auth);
    }
    
    public Auth findByUniqId(String uniqId) {
        return authRepository.findByUniqId(uniqId);
    }

}
