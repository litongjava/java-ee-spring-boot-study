package com.litongjava.study.spring.boot.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by ping-e-lee on 2021年6月24日 下午11:28:18 
 * @version 1.0 
 * @desc
 */
@RestController
@RequestMapping("hello")
public class HelloContoller {
  
  @RequestMapping("")
  public String hello() {
    return "hello,fdsfasfs,fdsfas";
  }
}
