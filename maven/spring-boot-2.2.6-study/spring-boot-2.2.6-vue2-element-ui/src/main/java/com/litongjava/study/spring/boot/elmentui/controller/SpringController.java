package com.litongjava.study.spring.boot.elmentui.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("spring")
@Slf4j
@CrossOrigin(origins = "*")
public class SpringController {

  @Autowired
  private ApplicationContext ac;


  @RequestMapping("beans")
  public String[] beans() {
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();
    return beanDefinitionNames;
  }
}
