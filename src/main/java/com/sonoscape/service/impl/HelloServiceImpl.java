package com.sonoscape.service.impl;

import com.sonoscape.service.IHelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHi(String page) {
        System.out.println("page..........................");
        return page;
    }
}
