package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {

    User findByEmail(String userName);

    List<User> findAll();
}
