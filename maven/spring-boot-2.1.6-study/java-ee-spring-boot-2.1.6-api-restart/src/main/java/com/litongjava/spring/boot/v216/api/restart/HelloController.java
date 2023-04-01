package com.litongjava.spring.boot.v216.api.restart;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bill white hat robot
 * @date 2020年9月10日_下午7:45:13 
 * @version 1.0 
 * @desc
 */

@RestController
public class HelloController {
  @RequestMapping("hello")
  public String hello() {
    return "helloxx";
  }
}
