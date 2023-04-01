package com.litong.spring.boot.v207.web.hello.controller;

import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("diagnostic")
public class DiagnosticController {
  @Autowired
  private ApplicationContext context;

  @RequestMapping("autoConfigurationBeans")
  public List<String> autoConfigurationBeans() {
    ArrayList<String> strings = new ArrayList<>();
    String[] beanDefinitionNames = context.getBeanDefinitionNames();
    for (String beanDefinitionName : beanDefinitionNames) {
      if (beanDefinitionName.endsWith("AutoConfiguration")) {
        System.out.println(beanDefinitionName);
        strings.add(beanDefinitionName);
      }
    }
    return strings;
  }
}
