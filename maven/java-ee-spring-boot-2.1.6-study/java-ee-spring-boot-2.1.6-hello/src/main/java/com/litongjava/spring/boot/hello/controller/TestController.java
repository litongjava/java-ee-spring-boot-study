package com.litongjava.spring.boot.hello.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by ping-e-lee on 2021年7月10日 下午7:02:44 
 * @version 1.0 
 * @desc
 */
@RequestMapping("test")
@RestController
public class TestController {

  @RequestMapping
  public String index() {
    String mesage="this is test ";
    System.out.println(mesage);
    return mesage;
  }
  
  
}
