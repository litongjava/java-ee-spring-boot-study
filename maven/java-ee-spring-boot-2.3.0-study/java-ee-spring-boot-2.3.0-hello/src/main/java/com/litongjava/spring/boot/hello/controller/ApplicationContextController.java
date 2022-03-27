package com.litongjava.spring.boot.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by ping-e-lee on 2021年6月29日 下午6:45:24 
 * @version 1.0 
 * @desc
 */
@RestController
@RequestMapping("application-context")
public class ApplicationContextController {

  @Autowired
  private ApplicationContext applicationContext;
  
  @RequestMapping("beans")
  public String[] beans() {
    String[] beanNames = applicationContext.getBeanDefinitionNames();
    return beanNames;
  }
}
