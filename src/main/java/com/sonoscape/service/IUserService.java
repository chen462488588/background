package com.sonoscape.service;

import com.sonoscape.entity.User;

import java.util.List;

public interface IUserService {
    User queryUserById(Integer id);

    List<User> userList();

    User login(User user);

    String batchInsert( );

    String deleteUserById(Integer id);

    boolean isExists(String username);

    User queryByUsername(String username);

    boolean checkIsExists(String username, String password);

    int addUser(User user);
}
