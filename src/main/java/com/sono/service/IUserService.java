package com.sono.service;

import com.sono.entity.User;

import java.util.List;

public interface IUserService {
    User queryUserById(Integer id);

    List<User> userList();

    User login(User user);

}
