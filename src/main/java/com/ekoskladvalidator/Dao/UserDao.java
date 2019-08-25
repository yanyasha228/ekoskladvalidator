package com.ekoskladvalidator.Dao;

import com.ekoskladvalidator.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}