package com.sonoscape.controller;

import com.sonoscape.entity.User;
import com.sonoscape.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    private IUserService userService;

    @RequestMapping("user/{id}")
    @ResponseBody
    public User queryUserById(@PathVariable Integer id) {
        User user = userService.queryUserById(id);
       return user;
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam("username") String username,
                        @RequestParam("password")String password){
        boolean isExists= userService.checkIsExists(username,password);
        if (isExists){
            return "success";
        }
        return "fail";

    }
}
