package com.litongjava.study.spring.boot.hello.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by litongjava@qq.com on 2020/8/5_15:50
 */
@RestController
@RequestMapping("hello")
public class HelloContorller {
  @RequestMapping
  public String hello(){
    return "hello";
  }
}
