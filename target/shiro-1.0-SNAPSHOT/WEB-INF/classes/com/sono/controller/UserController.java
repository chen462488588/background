package com.sono.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sono.entity.User;
import com.sono.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("users")
    @ResponseBody
    public List<User> getUserList(@RequestParam(required = false) Integer pageNum, @RequestParam(required = false) Integer pageSize) {
        PageHelper.startPage(pageNum == null ? 1 : pageNum, pageSize == null ? 3 : pageSize);
        List<User> users =  userService.userList();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo.getList();
    }
}

