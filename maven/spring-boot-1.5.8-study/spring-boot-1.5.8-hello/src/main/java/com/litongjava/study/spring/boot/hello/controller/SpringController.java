package com.litongjava.study.spring.boot.hello.controller;

import com.litongjava.study.spring.boot.hello.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("spring")
public class SpringController {

    @Autowired
    private ApplicationContext ac;
    public String[] beans(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        return beanDefinitionNames;
    }
}
