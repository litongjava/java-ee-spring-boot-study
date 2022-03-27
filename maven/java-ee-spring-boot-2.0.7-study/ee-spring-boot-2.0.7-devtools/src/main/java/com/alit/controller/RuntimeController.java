package com.alit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("runtime")
public class RuntimeController {

  @Autowired
  private ApplicationContext ac;

  @RequestMapping("beans")
  public String beans() {
    StringBuffer strBuf = new StringBuffer();
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();
    int i = 0;
    for (String b : beanDefinitionNames) {
      strBuf.append(++i + ":" + b + "\n");
    }
    return strBuf.toString();
  }
}
