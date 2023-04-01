package com.litongjava.spring.boot.jaspyt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by Ping E Lee on Sep 16, 2022 8:09:06 AM 
 *
 */
@RestController
@RequestMapping
public class IndexController {

  @Autowired
  private Environment environment;
  @RequestMapping
  public String index() {
    String property = environment.getProperty("us.name");
    return property;
  }
}
