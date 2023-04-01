package com.litong.java.sprong.boot.tomcat.listener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bill robot
 * @date 2020年9月14日_上午10:56:01 
 * @version 1.0 
 * @desc
 */
@RestController
@RequestMapping("/spring/context")
public class SpringContextController {

  @Autowired
  private ApplicationContext applicationContext;
  @RequestMapping("beans")
  public String[] beans() {
    String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
    return beanDefinitionNames;
  }
 }
