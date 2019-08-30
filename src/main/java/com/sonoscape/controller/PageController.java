package com.sonoscape.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("page")
public class PageController {

    @RequestMapping("page/{page}")
    public String page(@PathVariable(required = true) String page) {

        return page;
    }
}
