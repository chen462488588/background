package com.sono;

import com.sono.service.impl.HelloServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client2 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        HelloServiceImpl service = context.getBean("helloServiceImpl",HelloServiceImpl.class);
        System.out.println(service);
        service.sayHi("aaa");
    }
}
