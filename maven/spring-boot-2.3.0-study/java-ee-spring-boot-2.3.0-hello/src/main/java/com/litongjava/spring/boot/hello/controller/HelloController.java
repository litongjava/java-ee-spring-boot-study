package com.litongjava.spring.boot.hello.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by ping-e-lee on 2021年6月25日 下午2:38:19 
 * @version 1.0 
 * @desc
 */
@RestController
public class HelloController {
  @RequestMapping("hello")
  public String hello() {
    return "hello xxxs";
  }
}
