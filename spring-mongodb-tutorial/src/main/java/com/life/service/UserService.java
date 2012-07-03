package com.life.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.life.domain.Auth;
import com.life.domain.User;
import com.life.domain.UserServiceException;
import com.life.repository.AuthRepository;
import com.life.repository.PlanRepository;
import com.life.repository.RoleRepository;
import com.life.repository.UserRepository;
import com.life.service.auth.impl.AuthServiceImpl;

@Service
public class UserService
{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private AuthServiceImpl authService;

    public User create(User user)
    {
        // We must save both separately since there is no cascading feature
        // in Spring Data MongoDB (for now)
        if (user.getRole() != null)
            roleRepository.save(user.getRole());
        planRepository.save(user.getPlans());
        if(user.getAuth() != null)
        authRepository.save(user.getAuth());
        return userRepository.save(user);
    }

    public User read(User user)
    {
        return userRepository.findByUsername(user.getUsername());
    }

    public List<User> readAll()
    {
        return userRepository.findAll();
    }

    public User update(User user)
    {
        User existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser == null)
        {
            return null;
        }

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.getRole().setRole(user.getRole().getRole());
        existingUser.setPlans(user.getPlans());

        // We must save both separately since there is no cascading feature
        // in Spring Data MongoDB (for now)
        roleRepository.save(existingUser.getRole());
        planRepository.save(user.getPlans());
        return userRepository.save(existingUser);
    }

    public Boolean delete(User user)
    {
        User existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser == null)
        {
            return false;
        }

        // We must delete both separately since there is no cascading feature
        // in Spring Data MongoDB (for now)
        roleRepository.delete(existingUser.getRole());
        userRepository.delete(existingUser);
        return true;
    }

    public User findUserByUserName(String username)
    {
        return userRepository.findByUsername(username);
    }

    public Auth login(String username, String password)
    {
        User us = new User();
        us.setUsername(username);
        
        User checkedUser = read(us);
        Preconditions.checkNotNull(checkedUser, "User name couldnt find");
        if (checkedUser.isPasswordMatched(password))
        {
            Auth auth = Auth.createUnique();
            checkedUser.setAuth(auth);
            create(checkedUser);
            Auth persistendAuth = authService.findByUniqId(auth.getUniqId());
            persistendAuth.setUser(checkedUser);
            authRepository.save(persistendAuth);

            return persistendAuth;
        }
        else
        {
            throw new UserServiceException("Password didnt match");
        }
    }

    public User findUserById(String userId)
    {
        return userRepository.findById(userId);
    }

}
