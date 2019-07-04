package com.sono.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sono.entity.User;
import com.sono.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("users")
    @ResponseBody
    public PageInfo<User> getUserList(@RequestParam(required = false) Integer pageNum, @RequestParam(required = false) Integer pageSize) {
        PageHelper.startPage(pageNum == null ? 1 : pageNum, pageSize == null ? 3 : pageSize);
        List<User> users = userService.userList();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @RequestMapping("batchInsert")
    @ResponseBody
    public String batchInsert() {
        String result = userService.batchInsert();
        return result;
    }

    @RequestMapping("deleteUser")
    @ResponseBody
    public String deleteUser(@RequestParam(value = "id") Integer id) {
       String msg =  userService.deleteUserById(id);
       return msg;
    }
}

