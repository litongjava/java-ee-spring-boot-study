package com.litongjava.spring.boot.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litongjava.spring.boot.hello.services.HelloService;

/**
 * @author create by ping-e-lee on 2021年6月25日 下午2:51:33 
 * @version 1.0 
 * @desc
 */
@RestController
public class HelloController216{
  
  @Autowired
  private HelloService helloService;
  @RequestMapping("hello")
  public String hello() {
    System.out.println("HelloController216.hello()x");
    return helloService.hello();
  }


  @RequestMapping("classLoader")
  public String classLoader() {
    return this.getClass().getClassLoader().toString();
  }
}
