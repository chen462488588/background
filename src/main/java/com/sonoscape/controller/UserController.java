package com.sonoscape.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sonoscape.common.ResponseCode;
import com.sonoscape.entity.User;
import com.sonoscape.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;


    @RequestMapping("users")
    public PageInfo<User> getUserList(@RequestParam(required = false) Integer pageNum, @RequestParam(required = false) Integer pageSize) {
        PageHelper.startPage(pageNum == null ? 1 : pageNum, pageSize == null ? 3 : pageSize);
        List<User> users = userService.userList();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @RequestMapping("batchInsert")
    public String batchInsert() {
        String result = userService.batchInsert();
        return result;
    }

    @RequestMapping("deleteUser")
    public String deleteUser(@RequestParam(value = "id") Integer id) {
        String msg = userService.deleteUserById(id);
        return msg;
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public String addUser(  User user) {
        int row = userService.addUser(user);
        if (row == 1) {
            return ResponseCode.SUCCESS_CODE;
        }
        return ResponseCode.FAIL_CODE;
    }
}

