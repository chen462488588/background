package com.sonoscape.controller;

import com.sonoscape.service.IHelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

}
