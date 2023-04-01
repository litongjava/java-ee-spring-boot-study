package com.litong.spring.boot.v158.layui.v255.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bill robot
 * @date 2020年5月27日_下午9:35:05 
 * @version 1.0 
 * @desc
 */
@RestController
@RequestMapping
public class IndexController {

  @Autowired
  ApplicationContext applicationContext;
  @RequestMapping()
  private String[] getBeans() {
    String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
    return beanDefinitionNames;
  }
}
