package com.sono.controller;

import com.sono.entity.User;
import com.sono.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    private IUserService userService;

    @GetMapping("user/{id}")
    @ResponseBody
    public User queryUserById(@PathVariable Integer id) {
        User user = userService.queryUserById(id);
       return user;
    }

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password")String password){

        //创建subject实例
        Subject subject = SecurityUtils.getSubject();
        //判断当前用户是否登录
        if(subject.isAuthenticated()==false){
            //将用户名及密码封装交个UsernamePasswordToken
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            try {
                subject.login(token);
            } catch (AuthenticationException e) {
                System.out.println("验证不通过，无法登录！");
                return "error";
            }
        }
        return "success";

    }
}
