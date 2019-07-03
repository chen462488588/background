package com.sono.service.impl;

import com.sono.dao.UserMapper;
import com.sono.entity.User;
import com.sono.entity.UserExample;
import com.sono.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User queryUserById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public List<User> userList() {
        UserExample example = new UserExample();
        example.createCriteria().andIsactiveEqualTo(true);
        List<User> users = userMapper.selectByExample(example);
        return users;
    }

    @Override
    public User login(User user) {
        //TODO
        return null;
    }


}
