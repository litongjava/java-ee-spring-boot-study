package com.biillrobot.study.spring.validte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ViewResolver;

@RestController
@RequestMapping("bean")
public class BeanController {

  @Autowired
  private ViewResolver viewResolver;
  @Autowired
  private ApplicationContext context;

  @RequestMapping("viewResolver")
  public String viewResolver() {
    return viewResolver.toString();
  }
  
  @RequestMapping("beans")
  public String[] beans(){
    return context.getBeanDefinitionNames();
  }
}
