package com.sonoscape.service.impl;

import com.sonoscape.dao.UserMapper;
import com.sonoscape.entity.User;
import com.sonoscape.entity.UserExample;
import com.sonoscape.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
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
            User user = new User(null, "名字" + i, "昵称" + i, 25, "123", true);
            try {

                userMapper.insert(user);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("插入用户{}数据出错", user.toString());
                return "insert data error.数据：" + user.toString();
            }
        }
        return "insert data success!";
    }

    @Override
    public String deleteUserById(Integer id) {
        int row = userMapper.deleteByPrimaryKey(id);
        if (row == 0) {
            log.error("删除用户id为{}的操作失败。", id);
            return "删除用户id为" + id + "的操作失败";
        }
        return "删除成功";
    }

    @Override
    public boolean isExists(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        if (null != users && users.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public User queryByUsername(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        if (null != users && users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public boolean checkIsExists(String username, String password) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        if (null != users && users.size() > 0) {
            if (users.get(0).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int addUser(User user) {
        try {
            user.setPassword(DigestUtils.md5Hex(user.getPassword().getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            log.error("用户密码加密失败，异常信息：{}", e.getMessage());
        }
        return  userMapper.insert(user);
    }


}
