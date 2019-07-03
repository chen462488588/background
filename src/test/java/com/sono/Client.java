package com.sono;


import com.sono.controller.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Client {

    @Resource
    private HelloController helloController;
    @Test
    public void test() {
        System.out.println(helloController);
    }
}
