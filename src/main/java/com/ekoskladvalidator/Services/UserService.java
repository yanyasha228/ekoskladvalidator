package com.ekoskladvalidator.Services;


import com.ekoskladvalidator.Models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface UserService extends UserDetailsService {

    void save(User user);

    void delete(User user);

}
