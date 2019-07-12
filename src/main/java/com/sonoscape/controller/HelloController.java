package com.sonoscape.controller;

import com.alibaba.fastjson.JSON;
import com.sonoscape.entity.Student;
import com.sonoscape.service.IHelloService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;


@Controller
@Slf4j
public class HelloController {

    @Autowired
    private IHelloService helloService;

    @GetMapping("/test/{page}")
//    @ResponseBody
    public String test(@PathVariable String page) {
        log.error("page is :" + page);
        String a = helloService.sayHi(page);
        log.error("page||||" + a);

        return a;
    }

    //模拟登陆
    @GetMapping("login")
    @ResponseBody
    public String test(@RequestParam String name, @RequestParam String password) {
        Key key = new SecretKeySpec("mysecret".getBytes(), SignatureAlgorithm.HS512.getJcaName());
        Student student = new Student(name, password);
        String subject = JSON.toJSONString(student);
        Date exp = new Date(System.currentTimeMillis() + 2 * 60 * 1000);
        String jws = Jwts.builder().setSubject(subject).signWith(SignatureAlgorithm.HS512, key).setExpiration(exp).compact();
        return JSON.toJSONString(jws);
    }

    //模拟需要验证的接口
    @GetMapping("test2")
    @ResponseBody
    public String test1() {

        return "verify ok!";
    }
}
