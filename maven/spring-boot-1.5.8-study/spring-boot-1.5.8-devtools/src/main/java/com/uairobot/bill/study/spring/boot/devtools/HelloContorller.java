package com.uairobot.bill.study.spring.boot.devtools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by litongjava@qq.com on 2020/8/5_15:50
 */
@RestController
@RequestMapping("hello")
public class HelloContorller {
  @Autowired
  private ApplicationContext ac;
  @RequestMapping
  public String hello(){
    return "hello hxxx";
  }

  @RequestMapping("classLolaer")
  public String classLolaer() {
    //org.springframework.boot.devtools.restart.classloader.RestartClassLoader@16fb7593
    return this.getClass().getClassLoader().toString();
  }
  
  @RequestMapping("beans")
  public String[] beans() {
    return ac.getBeanDefinitionNames();
    
  }
}
