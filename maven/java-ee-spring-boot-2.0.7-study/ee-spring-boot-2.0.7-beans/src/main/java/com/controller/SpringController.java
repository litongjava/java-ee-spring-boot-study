package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("spring")
public class SpringController {

  @Autowired
  private ApplicationContext applicationContext;

  @RequestMapping("bean")
  public String[] bean() {
    String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
    return beanDefinitionNames;
  }

  @RequestMapping("auto-configuration")
  public List<String> autoConfiguration() {
    String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
    List<String> beans = new ArrayList<>();
    for (String b : beanDefinitionNames) {
      if (b.contains("AutoConfiguration") && !b.contains("$")) {
        beans.add(b);
      }
    }
    return beans;
  }
}
