package com.sono.service.impl;

import com.sono.dao.UserMapper;
import com.sono.entity.User;
import com.sono.entity.UserExample;
import com.sono.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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

    @Override
    public String batchInsert() {
        for (int i = 0; i < 1000; i++) {
          User  user = new User(null, "名字"+i, "昵称"+i, 25, "123", true);
          try {

            userMapper.insert(user);
          }
          catch (Exception e){
              e.printStackTrace();
              log.error("插入用户{}数据出错", user.toString());
              return  "insert data error.数据："+user.toString();
          }
        }
        return "insert data success!";
    }


}
